package com.cyh;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyh.consts.Constants;
import com.cyh.data.structure.Graph;
import com.cyh.factory.CalculatorFactory;
import com.cyh.utils.AssertUtils;
import com.cyh.utils.TypeConvertUtils;

/**
 * @author: CYH
 * @date: 2019/4/21
 */
public class Bootstrap {

    private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrap.class);

    public static void main(String[] args) {
        try {
            new Bootstrap().start(args);
        } catch (Throwable t) {
            LOGGER.error("Unexpected exception throw when application running", t);
            System.exit(-1);
        }
    }

    private void start(String[] args) throws IOException, URISyntaxException {
        List<String> allLines;
        try {
            allLines = readInputFileContent(args);
        } catch (Exception e) {
            LOGGER.error("Exception happened during reading input file. args: {}", Arrays.toString(args), e);
            throw e;
        }
        AssertUtils.isTrue(Objects.nonNull(allLines) && allLines.size() > 0, "没有读取到文件内容");
        String graphDescription = allLines.get(0).trim();
        initGraph(graphDescription);
        handleRequest(allLines);
    }

    private List<String> readInputFileContent(String[] args) throws IOException, URISyntaxException {
        String fileName = (args.length >= 1 ? args[0] : Constants.INPUT_FILE_NAME);
        URI uri = this.getClass().getClassLoader().getResource(fileName).toURI();
        Path path = Paths.get(uri);
        return Files.readAllLines(path, StandardCharsets.UTF_8);
    }

    private void initGraph(String graphDescription) {
        String[] desc = graphDescription.split(Constants.GRAPH_DESCRIPTION_SEPARATOR);
        for (String x : desc) {
            AssertUtils.isTrue(x.length() >= 3, "非法输入的图信息: " + x);
            int start = TypeConvertUtils.upperCharToInt(x.charAt(0));
            int end = TypeConvertUtils.upperCharToInt(x.charAt(1));
            int distance = Integer.valueOf(x.substring(2));
            Graph.getInstance().setDistance(start, end, distance);
        }
    }

    private void handleRequest(List<String> allLines) {
        for (int index = 1; index < allLines.size(); index++) {
            String input = allLines.get(index).trim();
            String result = null;
            try {
                result = CalculatorFactory.calculate(input);
            } catch (Exception e) {
                LOGGER.warn("Failed to handle the input line: {}", input, e);
            }
            System.out.println(String.format("Output #%d: %s", index, result));
        }
    }

}
