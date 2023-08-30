// package dev.java.odaat.Utils;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.core.io.Resource;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.jdbc.core.ResultSetExtractor;
// import org.springframework.stereotype.Component;
// import org.springframework.util.FileCopyUtils;

// import dev.java.odaat.Entity.Note;

// import java.io.IOException;
// import java.nio.charset.StandardCharsets;
// import java.sql.ResultSet;
// import java.sql.Statement;
// import java.util.Collection;


// // This is a way by which basic JDBC connections can be made in Spring.
// // @Component
// public class DatabaseInitializer {

//     @Value("classpath:ddl/users_table.sql")
//     private Resource usersTableSql;

//     private final JdbcTemplate jdbcTemplate;

//     @Autowired
//     public DatabaseInitializer(JdbcTemplate jdbcTemplate) {
//         this.jdbcTemplate = jdbcTemplate;
//     }

//     // @Override
//     // public void run(String... args) throws Exception {
//     //     executeSqlFile(usersTableSql);
//     // }

//     private void executeSqlFile(Resource sqlResource) throws Exception {

//             String dropifexists = "DROP TABLE users;";
//             jdbcTemplate.execute(dropifexists);

//             String create = new String(FileCopyUtils.copyToByteArray(sqlResource.getInputStream()), StandardCharsets.UTF_8);
//             jdbcTemplate.execute(create);

//             System.out.println("CREATED.");

//             String select = "SELECT * FROM users;";

//             jdbcTemplate.query(
//             select, new Object[] { },
//             (rs, rowNum) -> {
//                 System.out.println(rs);
//                 return new Note ();
//             }
//         );
//   }
// }
