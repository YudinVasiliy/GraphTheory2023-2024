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

    def edmonds_karp(self, source, sink):
        # создаём словарь для хранения родительских вершин в увеличивающем пути
        parent = {}
        max_flow = 0

        # цикл пока есть путь из источника в сток
        while self.bfs(source, sink, parent):
            # изначально пускаем бесконечный поток
            path_flow = float("inf")
            s = sink
            # ищем минимальный поток
            while s != source:
                path_flow = min(path_flow, self.graph[parent[s]][s])
                # переходим к "родителю" вершины
                s = parent[s]

            # увеличили максимальный поток на найденное значение
            max_flow += path_flow
            # обновляем пропускные способности в графе
            v = sink
            while v != source:
                u = parent[v]
                self.graph[u][v] -= path_flow
                self.graph[v][u] += path_flow
                v = parent[v]
            # очистили словарь использованных вершин
            parent = {}
        return max_flow

# Проверка на тестовом примере:
test_graph_file = "test_4.txt"
with open(test_graph_file, 'r') as file:
    count_vertices, count_edges = map(int, file.readline().split())
source = 1
sink = count_vertices

graph_test = Graph.create_from_file(test_graph_file)
print("Максимальный поток (алгоритм Edmonds–Karp):", graph_test.edmonds_karp(source, sink))
