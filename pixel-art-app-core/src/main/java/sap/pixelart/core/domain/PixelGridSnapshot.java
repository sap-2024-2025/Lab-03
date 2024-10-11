package sap.pixelart.core.domain;

/**
 * 
 * A snapshot of the pixel grid state
 * 
 */
public record PixelGridSnapshot(int numRows, int numColumns, RGBColor[][] pixels) {}
