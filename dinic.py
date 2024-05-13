from collections import defaultdict, deque

class Graph:
    def __init__(self):
        self.graph = defaultdict(dict)

    def add_edge(self, u, v, capacity):
        # Добавляем прямое ребро с заданной пропускной способностью
        self.graph[u][v] = capacity
        # если обратное ребро отсутствует - добавляем 
        if u not in self.graph[v]:
            self.graph[v][u] = 0

    @classmethod
    #строим граф по текстовому файлу
    def create_from_file(cls, graph_file):
        graph = cls()
        with open(graph_file, 'r') as file:
            # 1 строка - число вершин, число рёбер
            count_vertices, count_edges = map(int, file.readline().split())
            # описание рёбер графа - исходящая вершина ребра, входящая вершина ребра, пропускная способность
            for i in range(count_edges):
                u, v, capacity = map(int, file.readline().split())
                graph.add_edge(u, v, capacity)
        return graph

    # breadth-first search
    def bfs(self, source, target, parent):
        # создаём множество непосещённых вершин
        visited = set()
        # создаем очередь для вершин
        queue = deque()
        # начинаем обход с вершины источника
        queue.append(source)
        visited.add(source)

        while queue:
            # удаляем из очереди посещённую вершину
            u = queue.popleft()
            # заносим в очередь непосещённых соседей текущей вершины
            for v in self.graph[u]:
                if v not in visited and self.graph[u][v] > 0:
                    queue.append(v)
                    parent[v] = u
                    visited.add(v)
        # если не дошли до target возвращаем false
        return True if target in visited else False

    def dinic(self, source, sink):
        max_flow = 0

        while self.bfs(source, sink, {}):
            # Создаем слоистую сеть
            level = [-1] * len(self.graph)
            level[source-1] = 0 
            queue = deque([source])

            while queue:
                u = queue.popleft()
                for v, capacity in self.graph[u].items():
                    if capacity > 0 and level[v-1] == -1: 
                        level[v-1] = level[u-1] + 1
                        queue.append(v)
            # Если сток недостижим, заканчиваем
            if level[sink-1] == -1: 
                break  

            # используем dfs, чтобы найти блокирующий поток
            while True:
                blocking_flow = self.dfs(source, sink, float("inf"), level, {})
                if not blocking_flow:
                    break
                max_flow += blocking_flow

        return max_flow

    # depth-first search 
    def dfs(self, u, sink, flow, level, blocked):
        if u == sink:
            return flow

        for v, capacity in self.graph[u].items():
            if capacity > 0 and level[v-1] == level[u-1] + 1:
                if v not in blocked:
                    blocking_flow = self.dfs(v, sink, min(flow, capacity), level, blocked)
                    if blocking_flow:
                        self.graph[u][v] -= blocking_flow
                        self.graph[v][u] += blocking_flow
                        return blocking_flow
                else:
                    blocked[v] = True

        return 0

# Проверка на тестовом примере:
test_graph_file = "test_4.txt"
with open(test_graph_file, 'r') as file:
    count_vertices, count_edges = map(int, file.readline().split())
source = 1
sink = count_vertices

graph_test = Graph.create_from_file(test_graph_file)
print("Максимальный поток (алгоритм Диница):", graph_test.dinic(source, sink))
