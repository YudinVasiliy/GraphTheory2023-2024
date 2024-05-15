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
    def web(self, source, target):
        #print("web")
        # Создаем слоистую сеть
        level = [-1] * len(self.graph)
        level[source] = 0
        queue = deque([source])
        while queue:
            u = queue.popleft()
            for v, capacity in self.graph[u].items():
                if capacity > 0 and level[v] == -1:
                    level[v] = level[u] + 1
                    if (v == target):
                        # print("from web level: ", end='\t')
                        # for i in range(0, len(level)):
                        #     print(level[i], end='\t')
                        # print()
                        # print("from web level1: ", end="\t")
                        # for i in range(0, len(level)):
                        #     print(i, end='\t')
                        # print()
                        return level, True
                    queue.append(v)
        # достигли ли стока
        # print("from web level: ", end='\t')
        # for i in range(0, len(level)):
        #     print(level[i], end='\t')
        # print()
        # print("from web level1: ", end="\t")
        # for i in range(0, len(level)):
        #     print(i, end='\t')
        # print()
        return level, False

    def dinic(self, source, sink):
        max_flow = 0
        level, canReachSink=self.web(source, sink)
        while canReachSink:
            # используем dfs, чтобы найти блокирующий поток
            blocking_flow = self.dfs(source, sink, level)
            while blocking_flow:
                max_flow += blocking_flow
                blocking_flow = self.dfs(source, sink, level)
            level, canReachSink = self.web(source, sink)
        # print("GRAPH!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
        # for i in range(0, len(self.graph)):
        #     print(i,": ",self.graph[i].items())
        # print("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
        return max_flow

    # depth-first search
    def dfs(self, source, sink, level):
        blocked=set()
        # создаем очередь для вершин
        queue = deque()
        # начинаем обход с вершины источника
        blocked.add(source)
        u=source
        #maxFlow to i-1 layer of web
        maxFlow=[[float("inf"), -1]] * (level[sink]+1)
        maxFlow[0][1]=source
        while True:
            # заносим в очередь непосещённых соседей текущей вершины
            for v, capacity in self.graph[u].items():
                if v not in blocked and capacity > 0 and level[v] == level[u]+1:
                    queue.appendleft(v)
                    blocked.add(v)
            # print("new queue: ", queue)
            # удаляем из очереди посещённую вершину
            if(len(queue)>0):
                u = queue.popleft()
                # print("start: ",maxFlow[level[u] - 1][1], "end: ",u, "edge: ",self.graph[maxFlow[level[u] - 1][1]][u])
                maxFlow[level[u]] = [min(maxFlow[level[u] - 1][0],
                                             self.graph[maxFlow[level[u] - 1][1]][u]), u]
                # print("maxFlow: ",maxFlow)
                # print("--------------")
                if u==sink:
                    # print("DFS END")
                    return self.writeFlow(maxFlow,level[sink],level)
            else: break
        # если не дошли до target возвращаем 0
        # print("DFS END")
        return 0

    def writeFlow(self, maxFlow,sinkInd,level):
        maxFlowSize=maxFlow[sinkInd][0]
        # print("flow size: ",maxFlowSize)
        # print("sinkInd:",sinkInd)
        # print("WRITE FLOW SIZE: ",maxFlow)
        sinkLevel=level[maxFlow[sinkInd][1]]
        for i in range(1, sinkInd+1):
            u=maxFlow[i-1][1]
            v=maxFlow[i][1]
            self.graph[u][v] -= maxFlowSize
            self.graph[v][u] += maxFlowSize
            level[maxFlow[i][1]]=-1
        level[maxFlow[sinkInd][1]]=sinkLevel
        # print("from flow level: ",end='\t')
        # for i in range(0, len(level)):
        #     print(level[i], end='\t')
        # print()
        # print("from flow level1: ", end="\t")
        # for i in range(0, len(level)):
        #     print(i, end='\t')
        return maxFlowSize


# Проверка на тестовом примере:
test_graph_file = "MaxFlow-tests/test_d4.txt"
with open(test_graph_file, 'r') as file:
    count_vertices, count_edges = map(int, file.readline().split())
source = 0
sink = count_vertices-1

graph_test = Graph.create_from_file(test_graph_file)
print("Максимальный поток (алгоритм Диница):", graph_test.dinic(source, sink))