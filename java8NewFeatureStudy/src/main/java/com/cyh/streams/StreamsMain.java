package com.cyh.streams;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by yanhuche on 3/29/2017.
 */
public class StreamsMain {

    public static void main(String[] args) {
        new StreamsMain().run();
    }

    private void run() {
        final Collection<Task> tasks =
                Arrays.asList(new Task(Status.OPEN, 5), new Task(Status.OPEN, 13), new Task(Status.CLOSED, 8));
        // Calculate total points of all active tasks using sum()
        /*
         * 首先，tasks集合被转换成steam表示；
         * 其次，在steam上的filter操作会过滤掉所有CLOSED的task；
         * 第三，mapToInt操作基于每个task实例的Task::getPoints方法将task流转换成Integer集合；
         * 最后，通过sum方法计算总和，得出最后的结果。
         */
        final long totalPointsOfOpenTasks =
                tasks.stream().filter(task -> task.getStatus() == Status.OPEN).mapToInt(Task::getPoints).sum();
        System.out.println("Total points: " + totalPointsOfOpenTasks);

        // Calculate total points of all tasks
        final double totalPoints = tasks.stream().parallel().map(task -> task.getPoints()) // or map( Task::getPoints ) 
                .reduce(0, Integer::sum);
        System.out.println("Total points (all tasks): " + totalPoints);

        // Group tasks by their status
        final Map<Status, List<Task>> map = tasks.stream().collect(Collectors.groupingBy(Task::getStatus));
        System.out.println(map);

        // Calculate the weight of each tasks (as percent of total points) 
        final Collection<String> result = tasks.stream() // Stream< String >
                .mapToInt(Task::getPoints) // IntStream
                .asLongStream() // LongStream
                .mapToDouble(points -> points / totalPoints) // DoubleStream
                .boxed() // Stream< Double >
                .mapToLong(weigth -> (long) (weigth * 100)) // LongStream
                .mapToObj(percentage -> percentage + "%") // Stream< String> 
                .collect(Collectors.toList()); // List< String >
        System.out.println(result);
    }



    private enum Status {
        OPEN, CLOSED
    }

    private static final class Task {
        private final Status status;
        private final Integer points;

        Task(final Status status, final Integer points) {
            this.status = status;
            this.points = points;
        }

        public Integer getPoints() {
            return points;
        }

        public Status getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return String.format("[%s, %d]", status, points);
        }
    }

}
