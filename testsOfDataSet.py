import edmonds_karp,dinic
def testGraphFromFile(test_graph_file):
    print("test: ",test_graph_file)
    with open(test_graph_file, 'r') as file:
        count_vertices, count_edges = map(int, file.readline().split())
    source = 1
    sink = count_vertices
    graph_test = edmonds_karp.Graph.create_from_file(test_graph_file)
    resultEd=graph_test.edmonds_karp(source, sink)
    print("Максимальный поток (алгоритм Edmonds–Karp):", resultEd)

    graph_test = dinic.Graph.create_from_file(test_graph_file)
    resultDinic=graph_test.dinic(source-1, sink-1)
    print("Максимальный поток (алгоритм Диница):", resultDinic)
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