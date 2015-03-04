package de.jeha.fwj.jgrapht;

import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static de.jeha.fwj.jgrapht.ProcessingState.*;

public class ShortestPathTest {

    @Test
    public void testDijkstra() {
        Assert.assertNull(DijkstraShortestPath.findPathBetween(ProcessingStateGraph.STATE_GRAPH, IN_PROGRESS, ACKNOWLEDGED));

        List<DefaultEdge> path = DijkstraShortestPath.findPathBetween(ProcessingStateGraph.STATE_GRAPH, NEW, COMPLETED);
        for (DefaultEdge edge : path) {
            // System.out.println(edge.toString());
            System.out.println(ProcessingStateGraph.STATE_GRAPH.getEdgeSource(edge) + " -> "
                    + ProcessingStateGraph.STATE_GRAPH.getEdgeTarget(edge));
        }
    }

    @Test
    public void testInterpolate() {
        List<ProcessingState> interpolatedStates = interpolate(NEW, CANCELED);
        Assert.assertEquals(1, interpolatedStates.size());
        Assert.assertEquals(ACKNOWLEDGED, interpolatedStates.get(0));
    }

    public static List<ProcessingState> interpolate(ProcessingState source, ProcessingState target) {
        List<ProcessingState> interpolatedStates = new ArrayList<ProcessingState>();
        List<DefaultEdge> path = DijkstraShortestPath
                .findPathBetween(ProcessingStateGraph.STATE_GRAPH, source, target);
        if (path != null && path.size() > 1) {
            path.remove(0);
            for (DefaultEdge edge : path) {
                interpolatedStates.add(ProcessingStateGraph.STATE_GRAPH.getEdgeSource(edge));
            }
        }
        return interpolatedStates;
    }

}
