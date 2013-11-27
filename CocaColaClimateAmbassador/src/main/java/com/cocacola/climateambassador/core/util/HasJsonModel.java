package com.cocacola.climateambassador.core.util;

/**
 * Created by realandylawton on 9/7/13.
 */
public interface HasJsonModel<T> {
    T getModel();
    String getJsonAssetFilename();
}
