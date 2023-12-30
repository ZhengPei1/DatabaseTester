package Daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Arrays;

public class ProgrammerDaoImplementation implements ProgrammerDao<String> {

	@Override
	public String runQuery(String[] queryList, Connection connection) {

		StringBuilder result = new StringBuilder();
		result.append("\n--------------------------------------\n");

		// initialize statement
		Statement statement;
		try {
			statement = connection.createStatement();
		} catch (Exception e) {
			return e.getMessage();
		}

		for (String query : queryList) {
			try {
				// execute queries
				if (statement.execute(query)) {
					result.append("\n--------------------------------------");
					result.append("\n" + query + "\n");
					result.append("--------------------------------------\n");

					ResultSet resultSet = statement.getResultSet();
					ResultSetMetaData rsmd = resultSet.getMetaData();

					// print column names
					for (int i = 1; i <= rsmd.getColumnCount(); i++) {
						result.append(rsmd.getColumnName(i)).append("\t");
					}

					// print query result
					while (resultSet.next()) {
						result.append("\n");
						for (int i = 1; i <= rsmd.getColumnCount(); i++)
							result.append(resultSet.getString(i)).append("\t");
					}
					result.append("\n");

				} else {
					// print query result
					result.append("\n").append(statement.getUpdateCount())
							.append(" numbers of rows has been affected by this query: ").append(query).append("\n");
				}
			} catch (Exception e) {
				// append error messages
				result.append("\n").append(e.getMessage()).append("\nThis error is caused by the following query:\n")
						.append(query).append("\n");
			}
		}

		result.append("\n--------------------------------------\n");
		// return result
		return result.toString();

	}
}
