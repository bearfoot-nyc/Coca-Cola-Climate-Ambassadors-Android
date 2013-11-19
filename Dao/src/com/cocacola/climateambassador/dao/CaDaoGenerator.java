package com.cocacola.climateambassador.dao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;
import de.greenrobot.daogenerator.ToOne;

public class CaDaoGenerator {

    private static final int DB_VERSION = DbConstants.VERSION;

    public static void main(String[] args) throws Exception {

        Schema schema = new Schema(DbConstants.VERSION,
            "com.cocacola.climateambassador.data");

        // Section
        Entity section = schema.addEntity("Section");
        section.addIdProperty();
        section.addStringProperty("name");

        // Module
        Entity module = generateModules(schema, section);

        // Bullet Point Frame
        generateBulletPointFrame(schema, module);

        // Document
        generateDocuments(schema, module);


        // Generate the Dao
        DaoGenerator gen = new DaoGenerator();
        gen.generateAll(schema, DaoGenHelper.SRC_PATH);

    }

    private static Entity generateModules(Schema schema, Entity section) {

        // Module
        Entity module = schema.addEntity("Module");
        module.addIdProperty();
        module.addStringProperty("title");
        module.addStringProperty("bodyText");

        // Section To Modules
        Property sectionId = module.addLongProperty("sectionId").notNull().getProperty();
        ToMany sectionToModules = section.addToMany(module, sectionId);
        sectionToModules.setName("modules");

        return module;

    }

    private static void generateDocuments(Schema schema, Entity module) {
        // Document
        Entity document = schema.addEntity("Document");
        document.addIdProperty();
        document.addStringProperty("fileName");
        document.addStringProperty("label");

        // Module to Documents
        Property moduleId = document.addLongProperty("moduleId").notNull().getProperty();
        ToMany moduleToDocuments = module.addToMany(document, moduleId);
        moduleToDocuments.setName("documents");
    }

    private static void generateBulletPointFrame(Schema schema, Entity module) {

        // Bullet Point Frame
        Entity bulletPointFrame = schema.addEntity("BulletPointFrame");
        bulletPointFrame.addIdProperty();
        bulletPointFrame.addStringProperty("title");
        bulletPointFrame.addStringProperty("subtitle");

        // Module To Bullet Point Frame
        Property bulletPointFrameId = module.addLongProperty("bulletPointFrameId").getProperty();
        ToOne moduleToBulletPointFrame = module.addToOne(bulletPointFrame, bulletPointFrameId);
        moduleToBulletPointFrame.setName("bulletPointFrame");

        // Bullet Point
        Entity bulletPoint = schema.addEntity("BulletPoint");
        bulletPoint.addIdProperty();
        bulletPoint.addStringProperty("text");

        // Bullet Point Frame to Bullet Points
        Property frameId = bulletPoint.addLongProperty("bulletPointFrameId").notNull().getProperty();
        ToMany frameToBulletPoints = bulletPointFrame.addToMany(bulletPoint, frameId);
        frameToBulletPoints.setName("bulletPoints");

    }
}