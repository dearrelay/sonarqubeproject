package com.aenetworks.plugin.vbnet;

import java.util.List;

import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.config.Settings;
import org.sonar.api.resources.Qualifiers;
import org.sonar.plugins.dotnet.tests.CoverageAggregator;
import org.sonar.plugins.dotnet.tests.CoverageConfiguration;
import org.sonar.plugins.dotnet.tests.CoverageReportImportSensor;

import com.google.common.collect.ImmutableList;

public class VbNetCodeCoverageProvider {

	  private static final String CATEGORY = "C#";
	  private static final String SUBCATEGORY = "Code Coverage";

	  private static final String NCOVER3_PROPERTY_KEY = "sonar.cs.ncover3.reportsPaths";
	  private static final String OPENCOVER_PROPERTY_KEY = "sonar.cs.opencover.reportsPaths";
	  private static final String DOTCOVER_PROPERTY_KEY = "sonar.cs.dotcover.reportsPaths";
	  private static final String VISUAL_STUDIO_COVERAGE_XML_PROPERTY_KEY = "sonar.cs.vscoveragexml.reportsPaths";

	  private static final CoverageConfiguration COVERAGE_CONF = new CoverageConfiguration(
		VbNetPlugin.LANGUAGE_KEY,
	    NCOVER3_PROPERTY_KEY,
	    OPENCOVER_PROPERTY_KEY,
	    DOTCOVER_PROPERTY_KEY,
	    VISUAL_STUDIO_COVERAGE_XML_PROPERTY_KEY);

	  private VbNetCodeCoverageProvider() {
	  }

	  public static List extensions() {
	    return ImmutableList.of(
	    		VbNetCoverageAggregator.class,
	    		VbNetCoverageReportImportSensor.class,
	      PropertyDefinition.builder(NCOVER3_PROPERTY_KEY)
	        .name("NCover3 Reports Paths")
	        .description("Example: \"report.nccov\", \"report1.nccov,report2.nccov\" or \"C:/report.nccov\"")
	        .category(CATEGORY)
	        .subCategory(SUBCATEGORY)
	        .onlyOnQualifiers(Qualifiers.PROJECT, Qualifiers.MODULE)
	        .build(),
	      PropertyDefinition.builder(OPENCOVER_PROPERTY_KEY)
	        .name("OpenCover Reports Paths")
	        .description("Example: \"report.xml\", \"report1.xml,report2.xml\" or \"C:/report.xml\"")
	        .category(CATEGORY)
	        .subCategory(SUBCATEGORY)
	        .onlyOnQualifiers(Qualifiers.PROJECT, Qualifiers.MODULE)
	        .build(),
	      PropertyDefinition.builder(DOTCOVER_PROPERTY_KEY)
	        .name("dotCover (HTML) Reports Paths")
	        .description("Example: \"report.html\", \"report1.html,report2.html\" or \"C:/report.html\"")
	        .category(CATEGORY)
	        .subCategory(SUBCATEGORY)
	        .onlyOnQualifiers(Qualifiers.PROJECT, Qualifiers.MODULE)
	        .build(),
	      PropertyDefinition.builder(VISUAL_STUDIO_COVERAGE_XML_PROPERTY_KEY)
	        .name("Visual Studio (XML) Reports Paths")
	        .description("Example: \"report.coveragexml\", \"report1.coveragexml,report2.coveragexml\" or \"C:/report.coveragexml\"")
	        .category(CATEGORY)
	        .subCategory(SUBCATEGORY)
	        .onlyOnQualifiers(Qualifiers.PROJECT, Qualifiers.MODULE)
	        .build());
	  }

	  public static class VbNetCoverageAggregator extends CoverageAggregator {

	    public VbNetCoverageAggregator(Settings settings) {
	      super(COVERAGE_CONF, settings);
	    }

	  }

	  public static class VbNetCoverageReportImportSensor extends CoverageReportImportSensor {

	    public VbNetCoverageReportImportSensor(VbNetCoverageAggregator coverageAggregator, FileSystem fs) {
	      super(COVERAGE_CONF, coverageAggregator, fs);
	    }

	  }

	}