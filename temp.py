def bfs_level_order(graph, start):
    level_order = []  
    visited = set()
    visited.add(start)
    level_order.append([start])

    current_level = level_order[-1]  

    while current_level:
        next_level = []  

        for node in current_level:
            for neighbor in graph[node]:
                if neighbor not in visited:
                    visited.add(neighbor)  
                    next_level.append(neighbor)  

        if not next_level:
            break

        level_order.append(next_level)
        current_level = next_level  

    return level_order

graph = {
    'A': ['B', 'C'],
    'B': ['A', 'D', 'E'],
    'C': ['A', 'F'],
    'D': ['B'],
    'E': ['B', 'F'],
    'F': ['C', 'E']
}

result = bfs_level_order(graph, 'A')
print(result)
