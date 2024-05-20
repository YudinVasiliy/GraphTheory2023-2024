import dinic as d
import edmonds_karp as ed
import os
import time


def process_graphs_in_folder(folder_name, source, sink):
    timesDinic = []
    timesEdKarp = []
    for filename in os.listdir(folder_name):
        if filename.endswith(".txt"):
            graph = ed.Graph.create_from_file(os.path.join(folder_name, filename))
            start_time = time.time()
            graph.edmonds_karp(source, sink)
            end_time = time.time()
            elapsed_time = end_time - start_time
            timesEdKarp.append(elapsed_time)

            graph = d.Graph.create_from_file(os.path.join(folder_name, filename))
            start_time = time.time()
            graph.dinic(source-1, sink-1)
            end_time = time.time()
            elapsed_time = end_time - start_time
            timesDinic.append(elapsed_time)

    dinic_average_time = sum(timesDinic) / len(timesDinic)
    dinic_worst_time = max(timesDinic)
    ed_karp_average_time = sum(timesEdKarp) / len(timesEdKarp)
    ed_karp_worst_time = max(timesEdKarp)
    return dinic_average_time, dinic_worst_time, ed_karp_average_time, ed_karp_worst_time


if __name__ == "__main__":
    n = int(input("Введите число вершин (n): "))
    m = int(input("Введите число рёбер (m): "))
    U = int(input("Введите максимальную пропускную способность (U): "))

    folder_name = f"{n}_{m}_{U}"

    source = 1
    sink = n

    dinic_average_time, dinic_worst_time, ed_karp_average_time, ed_karp_worst_time = process_graphs_in_folder(folder_name, source, sink)
    print(f"Среднее время Диница для набора {folder_name} : {dinic_average_time:.6f} секунд")
    print(f"Худшее время Диница для набора {folder_name}: {dinic_worst_time:.6f} секунд")
    print(f"Среднее время Эдмондса-Карпа для набора {folder_name} : {ed_karp_average_time:.6f} секунд")
    print(f"Худшее время Эдмондса-Карпа для набора {folder_name}: {ed_karp_worst_time:.6f} секунд")
