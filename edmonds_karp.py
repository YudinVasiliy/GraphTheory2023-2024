from collections import deque
class Graph:
    def __init__(self):
        self.graph = []

    def add_edge(self, u, v, capacity):
        # Добавляем прямое ребро с заданной пропускной способностью
        self.graph[u-1][v-1]=capacity
        # если обратное ребро отсутствует - добавляем
        if (u-1) not in self.graph[v-1]:
            self.graph[v-1][u-1] = 0

    @classmethod
    #строим граф по текстовому файлу
    def create_from_file(cls, graph_file):
        graph = cls()
        with open(graph_file, 'r') as file:
            # 1 строка - число вершин, число рёбер
            count_vertices, count_edges = map(int, file.readline().split())
            graph.graph = [{} for _ in range(count_vertices)]
            # описание рёбер графа - исходящая вершина ребра, входящая вершина ребра, пропускная способность
            for i in range(count_edges):
                u, v, capacity = map(int, file.readline().split())
                graph.add_edge(u, v, capacity)
        # for i in range(0, len(graph.graph)):
        #     print(i,": ",graph.graph[i].items())
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
