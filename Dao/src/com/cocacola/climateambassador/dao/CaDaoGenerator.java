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
        section.addStringProperty("title");
        section.implementsInterface("Navigable");

        // Module
        Entity module = generateModules(schema, section);

        // Case
        Entity caseEntity = generateCases(schema, module);

        // Document
        // TODO Handle Case to Document relation
        generateDocuments(schema, module, caseEntity);

        // Bullet Point Frame
        // TODO Handle Case to Bullet Point relation
        generateBulletPointFrame(schema, module, caseEntity);

        // Generate the Dao
        DaoGenerator gen = new DaoGenerator();
        gen.generateAll(schema, DaoGenHelper.SRC_PATH);

    }

    private static Entity generateCases(Schema schema, Entity module) {

        // Module
        Entity caseEntity = schema.addEntity("CaCase");
        caseEntity.addIdProperty();
        caseEntity.addStringProperty("title");
        caseEntity.addStringProperty("bodyText");
        caseEntity.implementsInterface("Navigable");

        // Module to Cases
        Property moduleId = caseEntity.addLongProperty("moduleId").notNull().getProperty();
        ToMany moduleToCases = module.addToMany(caseEntity, moduleId);
        moduleToCases.setName("cases");

        // Text Frame
        Entity textFrame = schema.addEntity("TextFrame");
        textFrame.addIdProperty();
        textFrame.addStringProperty("title");
        textFrame.addStringProperty("bodyText");

        // Case to Text Frames
        Property caseId = textFrame.addLongProperty("caseId").getProperty();
        ToMany caseToTextFrames = caseEntity.addToMany(textFrame, caseId);
        caseToTextFrames.setName("textFrames");

        // Subtitle Text Pair
        Entity textPair = schema.addEntity("SubtitleTextPair");
        textPair.addStringProperty("title");
        textPair.addStringProperty("text");

        // Text Frame to Text Pairs
        Property textFrameId = textPair.addLongProperty("textFrameId").notNull().getProperty();
        ToMany textFrameToPairs = textFrame.addToMany(textPair, textFrameId);
        textFrameToPairs.setName("subtitleTextPairs");

        return caseEntity;

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

    private static void generateDocuments(Schema schema, Entity module, Entity caseEntity) {
        // Document
        Entity document = schema.addEntity("Document");
        document.addIdProperty();
        document.addStringProperty("fileName");
        document.addStringProperty("label");
        document.addBooleanProperty("isFavorite");

        // Module to Documents
        Property moduleId = document.addLongProperty("moduleId").getProperty();
        ToMany moduleToDocuments = module.addToMany(document, moduleId);
        moduleToDocuments.setName("documents");

        // Case to Documents (Case Studies)
        Property caseId = document.addLongProperty("caseId").getProperty();
        ToMany caseToDocuments = caseEntity.addToMany(document, caseId);
        caseToDocuments.setName("caseStudies");

    }

    private static void generateBulletPointFrame(Schema schema, Entity module, Entity caseEntity) {

        // Bullet Point Frame
        Entity bulletPointFrame = schema.addEntity("BulletPointFrame");
        bulletPointFrame.addIdProperty();
        bulletPointFrame.addStringProperty("title");
        bulletPointFrame.addStringProperty("subtitle");

        // Module To Bullet Point Frame
        Property bulletPointFrameId = module.addLongProperty("bulletPointFrameId").getProperty();
        ToOne moduleToBulletPointFrame = module.addToOne(bulletPointFrame, bulletPointFrameId);
        moduleToBulletPointFrame.setName("bulletPointFrame");

        // Case to Bullet Point Frame
        Property caseBulletPointFrameId = caseEntity.addLongProperty("bulletPointFrameId").getProperty();
        ToOne caseToBulletPointFrame = caseEntity.addToOne(bulletPointFrame, caseBulletPointFrameId);
        caseToBulletPointFrame.setName("bulletPointFrame");

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