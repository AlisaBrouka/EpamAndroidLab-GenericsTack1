package com.epam.impl;

import java.util.Set;

public interface GraphInterface <N extends Node> {

    Set <Edge<N>> getEdges();
    Set <N> getNodes();
}
