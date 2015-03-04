package de.jeha.fwj.jgrapht;


import static de.jeha.fwj.jgrapht.ProcessingState.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class ProcessingStateGraph {
    public static final DirectedGraph<ProcessingState, DefaultEdge> STATE_GRAPH;

    static {
        Map<ProcessingState, ProcessingState[]> transitionMap = new HashMap<ProcessingState, ProcessingState[]>();

        transitionMap.put(NEW, new ProcessingState[] { REJECTED, ACKNOWLEDGED });
        transitionMap.put(ACKNOWLEDGED, new ProcessingState[] { IN_PROGRESS, CANCELED });
        transitionMap.put(IN_PROGRESS, new ProcessingState[] { COMPLETED, CANCELED });

        STATE_GRAPH = new DefaultDirectedGraph<>(DefaultEdge.class);

        for (ProcessingState state : ProcessingState.values()) {
            STATE_GRAPH.addVertex(state);
        }
        for (Entry<ProcessingState, ProcessingState[]> entry : transitionMap.entrySet()) {
            ProcessingState current = entry.getKey();
            for (ProcessingState next : entry.getValue()) {
                STATE_GRAPH.addEdge(current, next);
            }
        }
    }
}
