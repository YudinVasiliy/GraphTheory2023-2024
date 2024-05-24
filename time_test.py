import graph_generator as gg
import graph as gr
import os
import time
import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
import networkx as nx


def process_graphs_in_folder(folder_name, source, sink):
    timesDinic = []
    timesEdKarp = []
    for filename in os.listdir(folder_name):
        if filename.endswith(".txt"):
            graph = gr.Graph.create_from_file(os.path.join(folder_name, filename))
            start_time = time.time()
            graph.edmonds_karp(source, sink)
            end_time = time.time()
            elapsed_time = end_time - start_time
            timesEdKarp.append(elapsed_time)

            graph = gr.Graph.create_from_file(os.path.join(folder_name, filename))
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

# Инициализация списков для хранения результатов
worstDinic = []
averageDinic = []
worstEdKarp = []
averageEdKarp = []

# Генерация графов и вычисление времени выполнения
for n in range(10, 101, 30):
    for m in range(1000, 10001, 500):
        for U in range(1000, 10001, 3000):
            m_act = m
            if m < n - 1:
                continue
            if m > n * (n - 1):
                m_act = n * (n - 1)
            folder_name = f"{n}_{m_act}_{U}"

            if(not os.path.exists(folder_name)):
                os.makedirs(folder_name, exist_ok=True)
                for i in range(1, 51):
                    graph = gg.generate_graph(n, m_act, U)
                    if not nx.is_weakly_connected(graph):
                        print("не слабосвязный")
                    filename = os.path.join(folder_name, f"{i}.txt")
                    gg.write_graph_to_file(graph, filename)

            source = 1
            sink = n
            dinic_avg, dinic_worst, ed_karp_avg, ed_karp_worst = process_graphs_in_folder(folder_name, source, sink)
            worstDinic.append((n, m, U, dinic_worst))
            averageDinic.append((n, m, U, dinic_avg))
            worstEdKarp.append((n, m, U, ed_karp_worst))
            averageEdKarp.append((n, m, U, ed_karp_avg))


# Преобразование данных в pandas DataFrame
columns = ['n', 'm', 'U', 'time']
df_worst_dinic = pd.DataFrame(worstDinic, columns=columns)
df_avg_dinic = pd.DataFrame(averageDinic, columns=columns)
df_worst_edkarp = pd.DataFrame(worstEdKarp, columns=columns)
df_avg_edkarp = pd.DataFrame(averageEdKarp, columns=columns)

# Функция для построения графиков
def plot_performance(df, x_label, y_label, title, filename):
    plt.figure(figsize=(10, 6))
    if(x_label == 'n'):
        for key, grp in df.groupby(['m', 'U']):
            plt.plot(grp[x_label], grp['time'], label=f'm={key[0]}, U={key[1]}')
        #x_values = np.linspace(0, 100, 100)
        #y_values = 0.000002 * x_values * x_values
        #plt.plot(x_values, y_values, label=f'y=0.000002 * x^2', color='red',linewidth=2)
    if(x_label == 'm'):
        for key, grp in df.groupby(['n', 'U']):
            plt.plot(grp[x_label], grp['time'], label=f'n={key[0]}, U={key[1]}')
        x_values = np.linspace(1000, 10000, 400)
        y_values = 0.0000025 * x_values
        plt.plot(x_values, y_values, label=f'y=0.0000025 * {x_label}', color='red',linewidth=2)
    if(x_label == 'U'):
        for key, grp in df.groupby(['n', 'm']):
            plt.plot(grp[x_label], grp['time'], label=f'n={key[0]}, m={key[1]}')
    plt.xlabel(x_label)
    plt.ylabel(y_label)
    plt.title(title)
    plt.legend()
    plt.grid(True)
    plt.savefig(filename)
    plt.show()

# Построение графиков для среднего и худшего времени выполнения алгоритмов

# Dinic Worst Time vs n
#plot_performance(df_worst_dinic, 'n', 'Time (Worst)', 'Dinic Worst Time vs Number of vertices (n)', 'Dinic_Worst_vs_n.png')

# Dinic Average Time vs n
#plot_performance(df_avg_dinic, 'n', 'Time (Average)', 'Dinic Average Time vs Number of vertices (n)', 'Dinic_Avg_vs_n.png')

# Dinic Worst Time vs m
#plot_performance(df_worst_dinic, 'm', 'Time (Worst)', 'Dinic Worst Time vs Number of edges (m)', 'Dinic_Worst_vs_m.png')

# Dinic Average Time vs m
plot_performance(df_avg_dinic, 'm', 'Time (Average)', 'Dinic Average Time vs Number of edges (m)', 'Dinic_Avg_vs_m.png')

# Dinic Worst Time vs U
#plot_performance(df_worst_dinic, 'U', 'Time (Worst)', 'Dinic Worst Time vs Maximum capacity (U)', 'Dinic_Worst_vs_U.png')

# Dinic Average Time vs U
#plot_performance(df_avg_dinic, 'U', 'Time (Average)', 'Dinic Average Time vs Maximum capacity (U)', 'Dinic_Avg_vs_U.png')

# Edmonds-Karp Worst Time vs n
#plot_performance(df_worst_edkarp, 'n', 'Time (Worst)', 'Edmonds-Karp Worst Time vs Number of vertices (n)', 'EdKarp_Worst_vs_n.png')

# Edmonds-Karp Average Time vs n
#plot_performance(df_avg_edkarp, 'n', 'Time (Average)', 'Edmonds-Karp Average Time vs Number of vertices (n)', 'EdKarp_Avg_vs_n.png')

# Edmonds-Karp Worst Time vs m
#plot_performance(df_worst_edkarp, 'm', 'Time (Worst)', 'Edmonds-Karp Worst Time vs Number of edges (m)', 'EdKarp_Worst_vs_m.png')

# Edmonds-Karp Average Time vs m
#plot_performance(df_avg_edkarp, 'm', 'Time (Average)', 'Edmonds-Karp Average Time vs Number of edges (m)', 'EdKarp_Avg_vs_m.png')

# Edmonds-Karp Worst Time vs U
#plot_performance(df_worst_edkarp, 'U', 'Time (Worst)', 'Edmonds-Karp Worst Time vs Maximum capacity (U)', 'EdKarp_Worst_vs_U.png')

# Edmonds-Karp Average Time vs U
#plot_performance(df_avg_edkarp, 'U', 'Time (Average)', 'Edmonds-Karp Average Time vs Maximum capacity (U)', 'EdKarp_Avg_vs_U.png')
