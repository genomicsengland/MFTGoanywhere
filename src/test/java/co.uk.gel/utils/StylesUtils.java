package co.uk.gel.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StylesUtils {

    private static final Logger log = LoggerFactory.getLogger(StylesUtils.class);

    public static String convertFontFaceStringToCSSProperty(String fontFace) {
        //example convert property Bold to equivalent CSS property 700
        return String.valueOf(FontWeight.findByName(fontFace).getWeight());
    }

    public static String convertFontColourStringToCSSProperty(String fontColor) {
        switch (fontColor) {
            case "#212b32":
                return "rgba(33, 43, 50, 1)";
            case "#dd2509":
                return "rgba(221, 37, 9, 1)";
            case "#005eb8":
                return "rgba(0, 94, 184, 1)";
            case "#da291c":
                return "rgba(218, 41, 28, 1)";
            case "#e5f6f5":
                return "rgba(229, 246, 245, 1)";
            case "#fdf3e5":
                return "rgba(253, 243, 229, 1)";
            case "#425563":
                return "rgba(66, 85, 99, 1)";
            case "#f0f0f0":
                return "rgba(240, 240, 240, 1)";
            case "#0961b7":
                return "rgba(9, 97, 183, 1)";
            case "#d1d5da":
                return "rgba(209, 213, 218, 1)";
            case "#777777":
                return "rgba(119, 119, 119, 1)";
            case "#005EB8":
                return "rgba(0, 79, 156, 1)";
            case "#eaebee":
                return "rgba(234, 235, 238, 1)";

            default:
                log.info("The colour passed as argument :"+fontColor+" not defined in switch case!");
                return "Not defined";
        }
    }
    public static String convertFontColourBorderColor(String fontColor) {
        switch (fontColor) {
            case "#dd2509":
                return "1px solid rgb(221, 37, 9)";
            default:
                throw new IllegalStateException("Unexpected value: " + fontColor);
        }
    }

}
