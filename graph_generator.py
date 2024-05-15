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


# Ввод параметров с консоли
n = int(input("Введите число вершин (n): "))
m = int(input("Введите число рёбер (m): "))
U = int(input("Введите максимальную пропускную способность (U): "))

# Создание папки для файлов
folder_name = f"{n}_{m}_{U}"
os.makedirs(folder_name, exist_ok=True)

# Генерация 50 графов и запись в файлы
for i in range(1, 51):
    graph = generate_graph(n, m, U)
    filename = os.path.join(folder_name, f"{i}.txt")
    write_graph_to_file(graph, filename)
    print(f"Граф {i} сохранён в файле {filename}")
