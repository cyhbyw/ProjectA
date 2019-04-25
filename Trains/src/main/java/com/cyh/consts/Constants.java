package com.cyh.consts;

import java.util.regex.Pattern;

/**
 * @author: CYH
 * @date: 2019/4/21
 */
public class Constants {

    /**
     * 输入文件名称
     */
    public static final String INPUT_FILE_NAME = "input.txt";

    /**
     * 图描述的分隔符
     */
    public static final String GRAPH_DESCRIPTION_SEPARATOR = ", ";

    /**
     * 最大的顶点数量
     */
    public static final Integer MAX_POINT_COUNT = 26;

    /**
     * 路径不存在时的输出信息
     */
    public static final String NO_SUCH_ROUTE = "NO SUCH ROUTE";

    /**
     * 正则表达式：计算指定路径的总距离
     * 使用 vertex 这个组来获取到顶点信息
     */
    public static final Pattern TOTAL_DISTANCE =
            Pattern.compile("The distance of the route (?<vertex>([A-Z](-[A-Z])+)).");

    /**
     * 正则表达式：给出最大停顿次数，计算方案数量
     * 使用了三个组： start end maximumStops 分别表示 起点、终点、最大停顿次数
     */
    public static final Pattern MAXIMUM_STOPS_TRIP_NUMBERS = Pattern.compile("The number of trips starting at"
            + " (?<start>[A-Z]) and ending at (?<end>[A-Z]) with a maximum of (?<maximumStops>(\\d)+) stops.");

    /**
     * 正则表达式：给出精确停顿次数，计算方案数量
     * 使用了三个组： start end exactlyStops 分别表示 起点、终点、精确停顿次数
     */
    public static final Pattern EXACTLY_STOPS_TRIP_NUMBERS = Pattern.compile("The number of trips starting at"
            + " (?<start>[A-Z]) and ending at (?<end>[A-Z]) with exactly (?<exactlyStops>(\\d)+) stops.");

    /**
     * 正则表达式：计算最短路径
     * 使用了两个组来分别获取 start end
     */
    public static final Pattern SHORTEST_ROUTE = Pattern.compile("The length of the shortest route"
            + " \\(in terms of distance to travel\\) from (?<start>[A-Z]) to (?<end>[A-Z]).");

    /**
     * 正则表达式：在总距离满足的前提下，计算方案数量
     * 使用了三个分组： start end maxDistance 分别表示 起点、终点、最大距离
     */
    public static final Pattern ROUT_NUMBER = Pattern.compile("The number of different routes from"
            + " (?<start>[A-Z]) to (?<end>[A-Z]) with a distance of less than (?<maxDistance>(\\d)+).");

}
