## Dijkstra algorithm pseudo-code:
* (written by Kfir Goldfarb)
### Complexity = O(|E| + |V|*log|V|)

* V - list of nodes (V is in G)
* neighbor(v) - return list neighbors of v
* weight(v, u) - return the weight between v and u
* s - start node
* e - end node
* Q - priority queue

```
dijkstra():
    prev[] - save prev nodes
    visited[] - mark nodes as visited
    dist[] - save destination of each node from start node
    n = |V|
    for (from i = 0 to n):
        prev[i] = nil
        dist[i] = infinity
        visited[i] = false
    *end-for
    
    Q.enqueue(s)
    dist[s] = 0
    
    while(!Q.empty()):
        u = Q.dequeue()
        for(v in neighbors(u)):
            if(visited[v] == false):
                if(dist[v] > dist[u] + weight(u, v)):
                    dist[v] = dist[u] + weight(u, v)
                    pred[v] = u
                    Q.enqueue(v)
                *end-if
            *end-if
        *end-for
        visited[u] = true
        if(u == e):
            break
        end-if
    *end-while
    
    // build path:
    
    k = e
    path = string(k)
    while(k != s):
        k = pred[k]
        path = string(k) + "->" + path
    *end-while
    return path
```

## Multi-Source Dijkstra algorithm pseudo-code: (written by Kfir Goldfarb)
### Complexity = O(|E| + |V|*log|V|)


* V - list of nodes
* Vr - list of nodes in reverse order
* neighbor(v) - return list neighbors of v
* neighborR(v) - return list neighbors of v (reversed)
* weight(v, u) - return the weight between v and u
* s - start node
* t - end node
* Q - priority queue
* Qr - priority queue (from reversed order)

```
biDijkstra():
    prev_s[] - save prev nodes
    visited_s[] - mark nodes as visited
    dist_s[] - save destination of each node from start node
    prev_t[] - save prev nodes from reversed order
    visited_t[] - mark nodes as visited from reversed order
    dist_t[] - save destination of each node from start node from reversed order
    n = |V|
    for (from i = 0 to n):
        prev_s[i] = nil
        dist_s[i] = infinity
        visited_s[i] = false
        prev_t[i] = nil
        dist_t[i] = infinity
        visited_t[i] = false
    *end-for
    
    Q.enqueue(s)
    Qr.enqueue(t)
    dist_s[s] = 0
    dist_t[t] = 0
    
    inProcess = true
    
    while(inProcess):
    
        while(!Q.empty()):
            u = Q.dequeue()
            for(v in neighbors(u)):
                if(visited_s[v] == false):
                    if(dist_s[v] > dist_s[u] + weight(u, v)):
                        dist_s[v] = dist_s[u] + weight(u, v)
                        pred_s[v] = u
                        Q.enqueue(v)
                    *end-if
                *end-if
            *end-for
            
            visited_s[u] = true
            if(visited_s[u] && visited_t[u])
                inProcess = false
            *end-if
        *end-while
        
        while(!Qr.empty()):
            u = Qr.dequeue()
            for(v in neighbors(u)):
                if(visited_t[v] == false):
                    if(dist_t[v] > dist_t[u] + weight(u, v)):
                        dist_t[v] = dist_t[u] + weight(u, v)
                        pred_t[v] = u
                        Qr.enqueue(v)
                    *end-if
                *end-if
            *end-for
            
            visited_t[u] = true
            if(visited_s[u] && visited_t[u])
                inProcess = false
            *end-if
        *end-while
    *end-while
    
    // get min and minIndex
    min = infinity
    minIndex = -1
    
    for (from i = 0 to n):
        if((visited_s[i] || visited_t[i]) && dist_s[i] != infinity && dist_t[i] != infinity):
            if(min > dist_s[i] + dist_t[i]):
                min = dist_s[i] + dist_t[i]
                minIndex = i
            *end-if
        *end-if
    *end-for
    
    // now dist = min
    
    // build path:
    
    path = ""
    pathR = ""
    
    k = minIndex
    while(prev_s[k] != -1):
        path = "->" + k + path;
        k = prev_s[k]
    *end-while
    path = k + path
    
    k = minIndex
    while(prev_t[k] != -1):
        pathR = "->" + k + pathR;
        k = prev_t[k]
    *end-while
    pathR = k + pathR
    
    pR[] = pathR.split("->")
    finalPath = path
    for (from i = pR.length -1; i >= 0; i--):
        finalPath += "->" + pR[i]
    *end-for
    
    
    return finalPath
```