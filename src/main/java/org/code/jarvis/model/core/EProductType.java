package org.code.jarvis.model.core;

/**
 * Created by KimChheng on 8/12/2017.
 */
public enum EProductType {

    WED("Wedding", "សំបុត្រការ"),
    CER("Ceremony", "សំបុត្របុណ្យ"),
    DES("Design", "សំបុត្រច្នៃ");

    public String desc;
    public String descKh;

    EProductType(String desc, String descKh) {
        this.desc = desc;
        this.descKh = descKh;
    }
}
