package dev.joangoma.testReporter;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import dev.joangoma.testReporter.gherkin.GherkinDialect;
import dev.joangoma.testReporter.gherkin.GherkinDialectManager;
import dev.joangoma.testReporter.gherkin.model.Asterisk;
import dev.joangoma.testReporter.gherkin.model.*;

import freemarker.template.utility.StringUtil;
import lombok.Getter;

/**
 * Allows {@link IGherkinFormatterModel} to be returned by using a name, from
 * the below gherkin model classes:
 * 
 * <ul>
 * <li>{@link Feature}</li>
 * <li>{@link Background}</li>
 * <li>{@link Scenario}</li>
 * <li>{@link Given}</li>
 * <li>{@link When}</li>
 * <li>{@link Then}</li>
 * <li>{@link And}</li>
 * <li>{@link But}</li>
 * </ul>
 * 
 * <p>
 * Example:
 * </p>
 * 
 * <pre>
 * extent.createTest(new GherkinKeyword("Feature"), "bddTest");
 * test.createNode(new GherkinKeyword("Scenario"), bddNode");
 * </pre>
 * 
 * @see IGherkinFormatterModel
 */
@Getter
public class GherkinKeyword {
    private static final Logger logger = Logger.getLogger(GherkinKeyword.class.getName());

    private Class<IGherkinFormatterModel> clazz = IGherkinFormatterModel.class;
    private IGherkinFormatterModel keyword;

    public GherkinKeyword(String gk) throws ClassNotFoundException {
        GherkinDialect dialect = null;
        String apiKeyword = StringUtil.capitalize(gk.trim());
        String refPath = clazz.getPackage().getName();

        try {
            apiKeyword = apiKeyword.equals("*") ? Asterisk.class.getSimpleName() : apiKeyword;
            dialect = GherkinDialectManager.getDialect();
            if (dialect != null
                    && !dialect.getLanguage().equalsIgnoreCase(GherkinDialectManager.getDefaultLanguage())) {
                apiKeyword = null;
                Map<String, List<String>> keywords = dialect.getKeywords();
                for (Entry<String, List<String>> key : keywords.entrySet()) {
                    apiKeyword = key.getValue().stream()
                            .filter(x -> x.trim().equalsIgnoreCase(gk.trim()))
                            .findAny()
                            .map(x -> StringUtil.capitalize(x))
                            .orElse(null);
                    if (apiKeyword != null) {
                        apiKeyword = StringUtil.capitalize(key.getKey());
                        break;
                    }
                }
            }
            if (apiKeyword == null)
                throw new GherkinKeywordNotFoundException("Keyword cannot be found. " +
                        "You supplied: " + gk + " for dialect " + dialect + " which couldn't be mapped.");
            if (apiKeyword.toLowerCase().contains("scenario") && apiKeyword.toLowerCase().contains("outline"))
                apiKeyword = "ScenarioOutline";
            String clazzName = refPath + "." + apiKeyword.replace(" ", "");
            Class<?> c = Class.forName(clazzName);
            keyword = (IGherkinFormatterModel) c.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logger.log(Level.SEVERE, "", e);
        }
    }
}