package com.cocacola.climateambassador.dao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class CaDaoGenerator {

    private static final int DB_VERSION = 1;

    public static void main(String[] args) throws Exception {

        Schema schema = new Schema(DB_VERSION, "com.cocacola.climateambassador.data");

        // Section
        Entity section = schema.addEntity("Section");
        section.addIdProperty();
        section.addStringProperty("name");

        // Module
        Entity module = schema.addEntity("Module");
        module.addIdProperty();
        section.addStringProperty("title");
        section.addStringProperty("bodyText");

        // Section To Modules
        Property sectionId = module.addLongProperty("sectionId").notNull().getProperty();
        ToMany sectionToModules = section.addToMany(module, sectionId);
        sectionToModules.setName("modules");

        // Document
        generateDocument(schema, module);

        // Bullet Point Frame
        generateBulletPointFrame(schema, module);

        // Generate the Dao
        DaoGenerator gen = new DaoGenerator();
        gen.generateAll(schema, DaoGenHelper.SRC_PATH);

    }

    private static void generateDocument(Schema schema, Entity module) {
        // Document
        Entity document = schema.addEntity("Document");
        document.addIdProperty();
        document.addStringProperty("fileName");
        document.addStringProperty("label");
        document.addStringProperty("fileType");

        // Module to Documents
        Property documentId = module.addLongProperty("documentId").notNull().getProperty();
        ToMany moduleToDocuments = module.addToMany(document, documentId);
        moduleToDocuments.setName("documents");
    }

    private static void generateBulletPointFrame(Schema schema, Entity module) {
        // Bullet Point Frame
        Entity bulletPointFrame = schema.addEntity("BulletPointFrame");
        bulletPointFrame.addIdProperty();
        bulletPointFrame.addStringProperty("title");
        bulletPointFrame.addStringProperty("subtitle");

        // Bullet Point
        Entity bulletPoint = schema.addEntity("BulletPoint");
        bulletPoint.addIdProperty();
        bulletPoint.addStringProperty("text");

        // Module To Bullet Point Frame
        Property bulletPointIdProperty = module.addLongProperty("bulletPointId").getProperty();
        module.addToOne(bulletPointFrame, bulletPointIdProperty);

        // Bullet Point Frame to Bullet Points
        Property bulletPointId = bulletPointFrame.addLongProperty("bulletPointId").notNull().getProperty();
        ToMany frameToBulletPoints = bulletPointFrame.addToMany(bulletPoint, bulletPointId);
        frameToBulletPoints.setName("bulletPoints");
    }
}