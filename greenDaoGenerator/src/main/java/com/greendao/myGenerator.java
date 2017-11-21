package com.greendao;
import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

    public class myGenerator {
        public static void main(String[] args) {


            Schema schema = new Schema(1, "com.example.ryan.firstassignment.db"); // Your app package name and the (.db) is the folder where the DAO files will be generated into.
            schema.enableKeepSectionsByDefault();

            addTables(schema);

            try {
                new DaoGenerator().generateAll(schema,"./app/src/main/java");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private static void addTables(final Schema schema) {
            addUserEntities(schema);
            // addPhonesEntities(schema);
        }

        // This is use to describe the colums of your table
        private static Entity addUserEntities(final Schema schema) {
            Entity user = schema.addEntity("User");
            user.addIdProperty().primaryKey().autoincrement();
            user.addIntProperty("user_id").notNull();
            user.addStringProperty("email");
            user.addStringProperty("password");
            return user;
        }

        //    private static Entity addPhonesEntities(final Schema schema) {
        //        Entity phone = schema.addEntity("Phone");
        //        phone.addIdProperty().primaryKey().autoincrement();
        //        phone.addIntProperty("user_id").notNull();
        //        phone.addStringProperty("number");
        //        return phone;
        //    }
    }
