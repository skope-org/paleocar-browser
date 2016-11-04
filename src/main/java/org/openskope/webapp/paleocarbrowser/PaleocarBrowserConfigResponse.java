package org.openskope.webapp.paleocarbrowser;

import java.util.Map;

public class PaleocarBrowserConfigResponse {
    
    private String paleocarBrowserBaseUrl;
	private String rasterTileServiceBaseUrl;
	private String rasterDataServiceBaseUrl;
	private Object dataSets;
	
	public PaleocarBrowserConfigResponse() {}

    public PaleocarBrowserConfigResponse(
        String paleocarBrowserBaseUrl, 
        String rasterTileServiceBaseUrl,
	    String rasterDataServiceBaseUrl,
	    Object dataSets
    ) {
        this();
        this.paleocarBrowserBaseUrl = paleocarBrowserBaseUrl;
	    this.rasterTileServiceBaseUrl = rasterTileServiceBaseUrl;
	    this.rasterDataServiceBaseUrl = rasterDataServiceBaseUrl;
	    this.dataSets = dataSets;
    }

    public String getPaleocarBrowserBaseUrl() {
        return this.paleocarBrowserBaseUrl;
    }

    public String getRasterTileServiceBaseUrl() {
        return this.rasterTileServiceBaseUrl;
    }

    public String getRasterDataServiceBaseUrl() {
        return this.rasterDataServiceBaseUrl;
    }
    
    public Object getDataSets() {
        return this.dataSets;
    }
}