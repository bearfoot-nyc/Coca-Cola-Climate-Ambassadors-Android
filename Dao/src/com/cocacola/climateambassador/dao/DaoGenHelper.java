package com.cocacola.climateambassador.dao;

import com.google.gson.annotations.SerializedName;
import de.greenrobot.daogenerator.PropertyType;
import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bobz on 7/19/13.
 */
public class DaoGenHelper {

    public static final String GEN_PACKAGE = "com.cocacola.climateambassador.data.dao";

    public static final String SRC_PATH = "../CocaColaClimateAmbassador/src/main/java";
    public static final String SRC_TEST_PATH = "src/java/com/socialradar/data/test";

    private static final String FULL_SRC_PATH =
        SRC_PATH + "/" + DaoGenHelper.GEN_PACKAGE.replaceAll("\\.", "/");

    public static Map<Class<?>, PropertyType> TYPE_MAP = null;

    static {
        TYPE_MAP = new HashMap<Class<?>, PropertyType>();
        TYPE_MAP.put(String.class, PropertyType.String);
        TYPE_MAP.put(Double.class, PropertyType.Double);
        TYPE_MAP.put(Boolean.class, PropertyType.Boolean);
        TYPE_MAP.put(Integer.class, PropertyType.Int);
    }

    public static String getDaoEntityName(Class srClazz) {
        String clazzName = srClazz.getSimpleName();
        if (clazzName.toLowerCase().startsWith("sr")) {
            clazzName = clazzName.substring(2);
        }
        clazzName = "Db" + clazzName;

        return clazzName;
    }

    public static Class getEntityClass(Class clazz) throws ClassNotFoundException {
        return Class.forName(GEN_PACKAGE + "." + getDaoEntityName(clazz));
    }

    public static Class getDaoClass(Class clazz) throws ClassNotFoundException {
        return Class.forName(GEN_PACKAGE + "." + getDaoEntityName(clazz) + "Dao");
    }

    public static Map<String, Field> getFieldNameClassMap(Class clazz) {
        Map<String, Field> map = new HashMap<String, Field>();
        for (Field field : clazz.getDeclaredFields()) {
            SerializedName serializedNameAnnotation = field.getAnnotation(SerializedName.class);
            if (null == serializedNameAnnotation) {
                System.out.println("No serialized name found for: " + field.getName());
                continue;
            }

            map.put(serializedNameAnnotation.value(), field);
        }

        return map;
    }

    public static boolean prepareDirectory() {
        // Remove all files in the outdir first
        File outDir = new File(FULL_SRC_PATH);
        if (outDir.exists()) {
            for (File child : outDir.listFiles())
                try {
                    String childName = child.getName();

                    boolean delete = true;
                    //                for (Class clazz : TO_CONVERT){
                    //                    String entityName = DaoGenHelper.getDaoEntityName(clazz);
                    //                    if (childName.matches(".*"+entityName+".*")){
                    //                        System.out.println("Keeping: "+childName);
                    //                        delete = false;
                    //                    }
                    //                }

                    //                for (String entityName : NEEDS_KEEP){
                    //                    if (childName.matches(".*"+entityName+".*")){
                    //                        System.out.println("Keeping: "+childName);
                    //                        delete = false;
                    //                    }
                    //                }
                    if (delete) {
                        System.out.println("Deleting: " + childName);
                        child.delete();
                    }
                } catch (Exception e) {
                    System.err.println("Failed to delete: " + child.getAbsolutePath());
                    return false;
                }
        } else {
            outDir.mkdirs();
        }
        return true;
    }
}
