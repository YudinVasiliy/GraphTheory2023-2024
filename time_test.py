import dinic as d
import edmonds_karp as ed
import graph_generator as gg
import os
import time
import pandas as pd
import matplotlib.pyplot as plt


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

worstDinic = []
averageDinic = []
worstEdKarp = []
averageEdKarp = []

for n in range(10, 101, 5):
    for m in range(1000, 10001, 500):
        for U in range(1000, 10001, 500):
            graph = gg.generate_graph(n, m, U)
            m_act = len(graph.edges())
            # Создание папки для файлов
            folder_name = f"{n}_{m_act}_{U}"
            os.makedirs(folder_name, exist_ok=True)

            # Генерация 50 графов и запись в файлы
            for i in range(1, 51):
                graph = gg.generate_graph(n, m, U)
                filename = os.path.join(folder_name, f"{i}.txt")
                gg.write_graph_to_file(graph, filename)

            source = 1
            sink = n

            dinic_avg, dinic_worst, ed_karp_avg, ed_karp_worst = process_graphs_in_folder(folder_name, source, sink)
            worstDinic.append((n, m, U, dinic_worst))
            averageDinic.append((n, m, U, dinic_avg))
            worstEdKarp.append((n, m, U, ed_karp_worst))
            averageEdKarp.append((n, m, U, ed_karp_avg))

            #print(f"Среднее время Диница для набора {folder_name} : {dinic_average_time:.6f} секунд")
            #print(f"Худшее время Диница для набора {folder_name}: {dinic_worst_time:.6f} секунд")
            #print(f"Среднее время Эдмондса-Карпа для набора {folder_name} : {ed_karp_average_time:.6f} секунд")
            #print(f"Худшее время Эдмондса-Карпа для набора {folder_name}: {ed_karp_worst_time:.6f} секунд")

print(f"Графы обработаны")
# Преобразование данных в pandas DataFrame
columns = ['n', 'm', 'U', 'time']
df_worst_dinic = pd.DataFrame(worstDinic, columns=columns)
df_avg_dinic = pd.DataFrame(averageDinic, columns=columns)
df_worst_edkarp = pd.DataFrame(worstEdKarp, columns=columns)
df_avg_edkarp = pd.DataFrame(averageEdKarp, columns=columns)

# Функция для построения графиков
def plot_performance(df, algo_name, metric_name):
    fig, axs = plt.subplots(3, 1, figsize=(10, 18))
    
    # Зависимость от n
    for m in df['m'].unique():
        for U in df['U'].unique():
            subset = df[(df['m'] == m) & (df['U'] == U)]
            axs[0].plot(subset['n'], subset['time'], label=f'm={m}, U={U}')
    axs[0].set_xlabel('Number of vertices (n)')
    axs[0].set_ylabel(f'Time ({metric_name})')
    axs[0].set_title(f'{algo_name} {metric_name} Time vs Number of vertices (n)')
    axs[0].legend()
    axs[0].grid(True)
    
    # Зависимость от m
    for n in df['n'].unique():
        for U in df['U'].unique():
            subset = df[(df['n'] == n) & (df['U'] == U)]
            axs[1].plot(subset['m'], subset['time'], label=f'n={n}, U={U}')
    axs[1].set_xlabel('Number of edges (m)')
    axs[1].set_ylabel(f'Time ({metric_name})')
    axs[1].set_title(f'{algo_name} {metric_name} Time vs Number of edges (m)')
    axs[1].legend()
    axs[1].grid(True)

    # Зависимость от U
    for n in df['n'].unique():
        for m in df['m'].unique():
            subset = df[(df['n'] == n) & (df['m'] == m)]
            axs[2].plot(subset['U'], subset['time'], label=f'n={n}, m={m}')
    axs[2].set_xlabel('Maximum capacity (U)')
    axs[2].set_ylabel(f'Time ({metric_name})')
    axs[2].set_title(f'{algo_name} {metric_name} Time vs Maximum capacity (U)')
    axs[2].legend()
    axs[2].grid(True)

    plt.tight_layout()
    plt.show()

# Построение графиков для среднего и худшего времени выполнения алгоритмов
plot_performance(df_worst_dinic, 'Dinic', 'Worst')
plot_performance(df_avg_dinic, 'Dinic', 'Average')
plot_performance(df_worst_edkarp, 'Edmonds-Karp', 'Worst')
plot_performance(df_avg_edkarp, 'Edmonds-Karp', 'Average')

#folder_name = f"worst_case_dinic_100_10000_10"

#source = 1
#sink = 100

#dinic_average_time, dinic_worst_time, ed_karp_average_time, ed_karp_worst_time = process_graphs_in_folder(folder_name, source, sink)
#print(f"Среднее время Диница для worst-case {folder_name} : {dinic_average_time:.6f} секунд")
#print(f"Худшее время Диница для worst-case {folder_name}: {dinic_worst_time:.6f} секунд")
#print(f"Среднее время Эдмондса-Карпа для worst-case {folder_name} : {ed_karp_average_time:.6f} секунд")
#print(f"Худшее время Эдмондса-Карпа для worst-case {folder_name}: {ed_karp_worst_time:.6f} секунд")

