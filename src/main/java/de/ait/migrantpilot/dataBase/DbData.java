package de.ait.migrantpilot.dataBase;

public class DbData {

    public static final String dbUrl = "jdbc:mysql://localhost:3306/migrantpilot_db";

    public static final String username = "root";

    public static final String password = "root";

    //query if your Beekeeper collapses hyphens (-)
    public static final String GET_CONFIRM_CODE_TEMPLATE =
            "SELECT LOWER(CONCAT(HEX(SUBSTRING(id, 1, 4)), '-', " +
                    "HEX(SUBSTRING(id, 5, 2)), '-', " +
                    "HEX(SUBSTRING(id, 7, 2)), '-', " +
                    "HEX(SUBSTRING(id, 9, 2)), '-', " +
                    "HEX(SUBSTRING(id, 11)))) AS formatted_id " +
                    "FROM confirm_code " +
                    "WHERE user_id = UNHEX(REPLACE('%s', '-', ''));";

    //query if your Beekeeper works as standard
//    public static final String GET_CONFIRM_CODE_TEMPLATE =
//            "SELECT id FROM confirm_code WHERE user_id = '%s'";

    public static final String DELETE_USER = "DELETE FROM app_user WHERE email='%s';";
}
