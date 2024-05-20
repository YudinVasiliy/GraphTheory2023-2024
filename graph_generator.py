import os
import random
import networkx as nx

def generate_graph(n, m, U):
    graph = nx.gnm_random_graph(n, m, directed=True)
    for u, v in graph.edges():
        graph[u][v]['capacity'] = random.randint(1, U)
    return graph

def write_graph_to_file(graph, filename):
    with open(filename, 'w') as file:
        file.write(f"{graph.number_of_nodes()} {graph.number_of_edges()}\n")
        for u, v, data in graph.edges(data=True):
            file.write(f"{u+1} {v+1} {data['capacity']}\n")  # увеличиваем на 1, так как вершины нумеруются с 0

def generate_worst_case_graph_dinic(n, capacity_large, capacity_small, filename):
    edges = []
    source = 0
    sink = n - 1

    # Создаем два сильно связанных компонента
    for i in range(1, n // 2):
        edges.append((source, i, capacity_large))
        edges.append((i, sink, capacity_small))

    for i in range(n // 2, n - 1):
        edges.append((source, i, capacity_large))
        edges.append((i, sink, capacity_small))

    # Записываем граф в файл
    with open(filename, 'w') as file:
        file.write(f"{n} {len(edges)}\n")
        for u, v, capacity in edges:
            file.write(f"{u + 1} {v + 1} {capacity}\n")


# Ввод параметров с консоли
# n = int(input("Введите число вершин (n): "))
# m = int(input("Введите число рёбер (m): "))
# U = int(input("Введите максимальную пропускную способность (U): "))

# Создание папки для файлов
# folder_name = f"{n}_{m}_{U}"
# os.makedirs(folder_name, exist_ok=True)

# Генерация 50 графов и запись в файлы
# for i in range(1, 51):
#     graph = generate_graph(n, m, U)
#     filename = os.path.join(folder_name, f"{i}.txt")
#     write_graph_to_file(graph, filename)
#     print(f"Граф {i} сохранён в файле {filename}")

    

#n = int(input("Введите число вершин (n): "))
#capacity_large = int(input("Введите большую пропускную способность: "))
#capacity_small = int(input("Введите малую пропускную способность: "))

#folder_name = f"worst_case_dinic_{n}_{capacity_large}_{capacity_small}"
#os.makedirs(folder_name, exist_ok=True)

#for i in range(1, 51):
#    filename = os.path.join(folder_name, f"{i}.txt")
#    generate_worst_case_graph_dinic(n, capacity_large, capacity_small, filename)
#    print(f"Граф worst-case {i} сохранён в файле {filename}")

#print(f"Все графы сохранены в папке {folder_name}")
