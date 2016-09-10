package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MyClass {
    public static void main(String[] args){
        Schema schema = new Schema(1,"com.example.dllo.vmovie");
        addNote(schema);

        try {
            new DaoGenerator().generateAll(schema, "./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addNote(Schema schema) {
        Entity subScribe = schema.addEntity("SubScribeBean");
        subScribe.addIdProperty().autoincrement().primaryKey();
        subScribe.addStringProperty("seriesId");
        Entity likeFilm = schema.addEntity("LikeFilmBean");
        likeFilm.addIdProperty().autoincrement().primaryKey();
        likeFilm.addStringProperty("title");
        Entity likeBackStage = schema.addEntity("LikeBackStageBean");
        likeBackStage.addIdProperty().autoincrement().primaryKey();
        likeBackStage.addStringProperty("title");
    }
}
