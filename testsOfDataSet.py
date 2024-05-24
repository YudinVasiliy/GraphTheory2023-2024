import graph
import time

def testGraphFromFile(test_graph_file):
    print("test: ",test_graph_file)
    with open(test_graph_file, 'r') as file:
        count_vertices, count_edges = map(int, file.readline().split())
    source = 0
    sink = count_vertices-1
    graph_test = graph.Graph.create_from_file(test_graph_file)
    start_time = time.time()  # Записываем время начала выполнения теста
    resultEd=graph_test.edmonds_karp(source, sink)
    end_time = time.time()  # Записываем время окончания выполнения теста
    execution_time = end_time - start_time  # Вычисляем время выполнения теста
    print("Максимальный поток (алгоритм Edmonds–Karp):", resultEd)
    print(f"Время выполнения теста: {execution_time} секунд")

    graph_test = graph.Graph.create_from_file(test_graph_file)
    start_time = time.time()  # Записываем время начала выполнения теста
    resultDinic=graph_test.dinic(source, sink)
    end_time = time.time()  # Записываем время окончания выполнения теста
    execution_time = end_time - start_time  # Вычисляем время выполнения теста
    print("Максимальный поток (алгоритм Диница):", resultDinic)
    print(f"Время выполнения теста: {execution_time} секунд")
    if(resultEd==resultDinic):print("OK")
    else: print("FALSE!!!!!!")

templates={"MaxFlow-tests/test_|.txt":["1","2","3","4","5","6"],
           "MaxFlow-tests/test_d|.txt":["1","2","3","4","5"],
           "MaxFlow-tests/test_rd0|.txt":["1","2","3","4","5","6","7"],
            "MaxFlow-tests/test_rl|.txt":["01","02","03","04","05","06","07","08","09","10"]}
for fileTemplate,toReplaceArr in templates.items():
    for toReplace in toReplaceArr:
        testGraphFromFile(fileTemplate.replace("|",toReplace))
        print("=====================================")