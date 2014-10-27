package util;

public enum UISelectorType {
    RESOURCE_ID("resourceID"),
    TEXT_CONTAINS("textContains"),
    TEXT("text"),
    TEXT_START_WITH("textStartWith"),
    SELECTED("selected"),
    CLASS_NAME("className");
    private String UISelectorCode;

    /**
     * init new UISelectorType
     *
     * @param s String
     */
    private UISelectorType(String s) {
        UISelectorCode = s;
    }

    /**
     * init new UISelectorType from String
     *
     * @return UISelectorType
     */
    public static UISelectorType fromString(String selectorTypeStr) {
        if (selectorTypeStr != null) {
            for (UISelectorType b : UISelectorType.values()) {
                if (selectorTypeStr.equalsIgnoreCase(b.UISelectorCode)) {
                    return b;
                }
            }
        }
        return null;
    }
}
