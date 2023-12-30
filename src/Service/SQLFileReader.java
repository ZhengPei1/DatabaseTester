package Service;

import Daos.ProgrammerDaoImplementation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// File reader
public final class SQLFileReader {
	// Constants
	private static final String MULTI_LINE_COMMENT_REGEX = "\\/\\*[^(\\*)]*\\*\\/";
	private static final String SINGLE_LINE_COMMENT_REGEX = "--[^\\n]*";
	private static final String LINE_SEPARATOR_REGEX = "[\\n]";
	private ProgrammerDaoImplementation programmerDaoImplementation = new ProgrammerDaoImplementation();

	// Statements list
	private String[] statements;

	public SQLFileReader(String script) {

		if(script != null){
			// Remove comments and line breaks
			script = script.replaceAll(MULTI_LINE_COMMENT_REGEX, "");
			script = script.replaceAll(SINGLE_LINE_COMMENT_REGEX, "");
			script = script.replaceAll(LINE_SEPARATOR_REGEX, " ");


			// Split the script into individual statements
			statements = script.split("[;]");
		}

	}

	public String executeQuery(Connection connection) {
		return programmerDaoImplementation.runQuery(statements, connection);
	}

}
