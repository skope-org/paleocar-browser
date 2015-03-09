package org.digitalantiquity.skope.service.file;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.digitalantiquity.skope.service.DoubleWrapper;
import org.digitalantiquity.skope.service.FeatureHelper;
import org.geojson.Feature;
import org.postgis.Point;
import org.postgis.Polygon;

import com.github.davidmoten.geo.Coverage;
import com.github.davidmoten.geo.GeoHash;

public class EnvelopeQuerySubTask implements Runnable {
    private final Logger logger = Logger.getLogger(getClass());

    private Polygon poly;
    private Feature feature;
    private EnvelopeQueryTask task;
    private int year;
    private int level;
    private FileService service;

    public EnvelopeQuerySubTask(EnvelopeQueryTask task,Polygon poly, FileService service, int level, int year) {
        this.task = task;
        this.poly = poly;
        this.service = service;
        this.level = level;
        this.year = year;
    }

    @Override
    public void run() {
        try {
            Point p1 = poly.getPoint(0);
            Point p2 = poly.getPoint(2);
            Coverage coverage = GeoHash.coverBoundingBoxMaxHashes(Math.max(p1.y, p2.y), Math.min(p1.x, p2.x), Math.min(p1.y, p2.y), Math.max(p1.x, p2.x), 40);
            DoubleWrapper dw = new DoubleWrapper();
            for (String hash : coverage.getHashes()) {
                File file = FileService.constructFileName(year,hash);
                if (file.exists()) {
                    Double val = Double.parseDouble(FileUtils.readLines(file).get(year));
                    dw.increment(val);
                }
            }

            if (dw != null && dw.getCount() > 0) {
                Double avg = dw.getAverage();
                logger.trace("adding " + avg + " for: " + poly);
                feature = FeatureHelper.createFeature(poly, avg);
                setFeature(feature);
                task.getFeatureCollection().add(feature);
            }
        } catch (Exception e) {
            logger.error(e,e);
        }
    }

    
    
    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

}