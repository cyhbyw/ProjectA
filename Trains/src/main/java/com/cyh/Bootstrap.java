package com.cyh;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.cyh.consts.Constants;
import com.cyh.data.structure.Graph;
import com.cyh.factory.CalculatorFactory;
import com.cyh.utils.TypeConvertUtils;

/**
 * @author: CYH
 * @date: 2019/4/19 0019 8:27
 */
public class Bootstrap {

    public static void main(String[] args) throws Exception {
        new Bootstrap().start();
    }

    private void start() throws IOException, URISyntaxException {
        List<String> allLines = readInputFileContent();
        String graphDescription = allLines.get(0).trim();
        initGraph(graphDescription);
        handleRequest(allLines);
    }

    private List<String> readInputFileContent() throws IOException, URISyntaxException {
        URI uri = this.getClass().getClassLoader().getResource(Constants.INPUT_FILE_NAME).toURI();
        Path path = Paths.get(uri);
        return Files.readAllLines(path, StandardCharsets.UTF_8);
    }

    private void initGraph(String graphDescription) {
        String[] desc = graphDescription.split(Constants.GRAPH_DESCRIPTION_SEPARATOR);
        Graph graph = Graph.getInstance();
        for (String x : desc) {
            int start = TypeConvertUtils.upperCharToInt(x.charAt(0));
            int end = TypeConvertUtils.upperCharToInt(x.charAt(1));
            int distance = Integer.valueOf(x.substring(2));
            graph.setDistance(start, end, distance);
        }
    }

    private void handleRequest(List<String> allLines) {
        for (int index = 1; index < allLines.size(); index++) {
            String input = allLines.get(index).trim();
            System.out.println(String.format("Case #%d: %s", index, CalculatorFactory.calculate(input)));
        }
    }

}
