import unittest
from edmonds_karp import Graph 
import time

class TestEdmondsKarpAlgorithm(unittest.TestCase):
    def test_1(self):
        filename = "test_1.txt"
        expected_max_flow = 935  # Ожидаемый максимальный поток для этого графа
        print('***************************')
        print('TEST_1     EXPECT:', expected_max_flow)
        with open(filename, 'r') as file:
            count_vertices, count_edges = map(int, file.readline().split())
        source = 1
        sink = count_vertices
        start_time = time.time()  # Записываем время начала выполнения теста
        
        g = Graph.create_from_file(filename)
        max_flow = g.edmonds_karp(source, sink)
        end_time = time.time()  # Записываем время окончания выполнения теста
        execution_time = end_time - start_time  # Вычисляем время выполнения теста
        
        print(f"Время выполнения теста 'test_1': {execution_time} секунд")
        self.assertEqual(max_flow, expected_max_flow)

    def test_2(self):
        filename = "test_2.txt"
        expected_max_flow = 2789  # Ожидаемый максимальный поток для этого графа
        print('***************************')
        print('TEST_2     EXPECT:', expected_max_flow)
        with open(filename, 'r') as file:
            count_vertices, count_edges = map(int, file.readline().split())
        source = 1
        sink = count_vertices
        start_time = time.time()  # Записываем время начала выполнения теста
        
        g = Graph.create_from_file(filename)
        max_flow = g.edmonds_karp(source, sink)
        end_time = time.time()  # Записываем время окончания выполнения теста
        execution_time = end_time - start_time  # Вычисляем время выполнения теста
        
        print(f"Время выполнения теста 'test_2': {execution_time} секунд")
        self.assertEqual(max_flow, expected_max_flow)

    def test_3(self):
        filename = "test_3.txt"
        expected_max_flow = 2000000  # Ожидаемый максимальный поток для этого графа
        print('***************************')
        print('TEST_3     EXPECT:', expected_max_flow)
        with open(filename, 'r') as file:
            count_vertices, count_edges = map(int, file.readline().split())
        source = 1
        sink = count_vertices
        start_time = time.time()  # Записываем время начала выполнения теста
        
        g = Graph.create_from_file(filename)
        max_flow = g.edmonds_karp(source, sink)
        end_time = time.time()  # Записываем время окончания выполнения теста
        execution_time = end_time - start_time  # Вычисляем время выполнения теста
        
        print(f"Время выполнения теста 'test_3': {execution_time} секунд")
        self.assertEqual(max_flow, expected_max_flow)

    def test_4(self):
        filename = "test_4.txt"
        expected_max_flow = 23  # Ожидаемый максимальный поток для этого графа
        print('***************************')
        print('TEST_4     EXPECT:', expected_max_flow)
        with open(filename, 'r') as file:
            count_vertices, count_edges = map(int, file.readline().split())
        source = 1
        sink = count_vertices
        start_time = time.time()  # Записываем время начала выполнения теста
        
        g = Graph.create_from_file(filename)
        max_flow = g.edmonds_karp(source, sink)
        end_time = time.time()  # Записываем время окончания выполнения теста
        execution_time = end_time - start_time  # Вычисляем время выполнения теста
        
        print(f"Время выполнения теста 'test_4': {execution_time} секунд")
        self.assertEqual(max_flow, expected_max_flow)

    def test_5(self):
        filename = "test_5.txt"
        expected_max_flow = 256  # Ожидаемый максимальный поток для этого графа
        print('***************************')
        print('TEST_5     EXPECT:', expected_max_flow)
        with open(filename, 'r') as file:
            count_vertices, count_edges = map(int, file.readline().split())
        source = 1
        sink = count_vertices
        start_time = time.time()  # Записываем время начала выполнения теста
        
        g = Graph.create_from_file(filename)
        max_flow = g.edmonds_karp(source, sink)
        end_time = time.time()  # Записываем время окончания выполнения теста
        execution_time = end_time - start_time  # Вычисляем время выполнения теста
        
        print(f"Время выполнения теста 'test_5': {execution_time} секунд")
        self.assertEqual(max_flow, expected_max_flow)

    def test_6(self):
        filename = "test_6.txt"
        expected_max_flow = 523  # Ожидаемый максимальный поток для этого графа
        print('***************************')
        print('TEST_6     EXPECT:', expected_max_flow)
        with open(filename, 'r') as file:
            count_vertices, count_edges = map(int, file.readline().split())
        source = 1
        sink = count_vertices
        start_time = time.time()  # Записываем время начала выполнения теста
        
        g = Graph.create_from_file(filename)
        max_flow = g.edmonds_karp(source, sink)
        end_time = time.time()  # Записываем время окончания выполнения теста
        execution_time = end_time - start_time  # Вычисляем время выполнения теста
        
        print(f"Время выполнения теста 'test_6': {execution_time} секунд")
        self.assertEqual(max_flow, expected_max_flow)

    def test_d1(self):
        filename = "test_d1.txt"
        expected_max_flow = 171  # Ожидаемый максимальный поток для этого графа
        print('***************************')
        print('TEST_D1     EXPECT:', expected_max_flow)
        with open(filename, 'r') as file:
            count_vertices, count_edges = map(int, file.readline().split())
        source = 1
        sink = count_vertices
        start_time = time.time()  # Записываем время начала выполнения теста
        
        g = Graph.create_from_file(filename)
        max_flow = g.edmonds_karp(source, sink)
        end_time = time.time()  # Записываем время окончания выполнения теста
        execution_time = end_time - start_time  # Вычисляем время выполнения теста
        
        print(f"Время выполнения теста 'test_d1': {execution_time} секунд")
        self.assertEqual(max_flow, expected_max_flow)

    def test_d2(self):
        filename = "test_d2.txt"
        expected_max_flow = 8023  # Ожидаемый максимальный поток для этого графа
        print('***************************')
        print('TEST_D2     EXPECT:', expected_max_flow)
        with open(filename, 'r') as file:
            count_vertices, count_edges = map(int, file.readline().split())
        source = 1
        sink = count_vertices
        start_time = time.time()  # Записываем время начала выполнения теста
        
        g = Graph.create_from_file(filename)
        max_flow = g.edmonds_karp(source, sink)
        end_time = time.time()  # Записываем время окончания выполнения теста
        execution_time = end_time - start_time  # Вычисляем время выполнения теста
        
        print(f"Время выполнения теста 'test_d2': {execution_time} секунд")
        self.assertEqual(max_flow, expected_max_flow)

    def test_d3(self):
        filename = "test_d3.txt"
        expected_max_flow = 9078  # Ожидаемый максимальный поток для этого графа
        print('***************************')
        print('TEST_D3     EXPECT:', expected_max_flow)
        with open(filename, 'r') as file:
            count_vertices, count_edges = map(int, file.readline().split())
        source = 1
        sink = count_vertices
        start_time = time.time()  # Записываем время начала выполнения теста
        
        g = Graph.create_from_file(filename)
        max_flow = g.edmonds_karp(source, sink)
        end_time = time.time()  # Записываем время окончания выполнения теста
        execution_time = end_time - start_time  # Вычисляем время выполнения теста
        
        print(f"Время выполнения теста 'test_d3': {execution_time} секунд")
        self.assertEqual(max_flow, expected_max_flow)

    def test_d4(self):
        filename = "test_d4.txt"
        expected_max_flow = 9072  # Ожидаемый максимальный поток для этого графа
        print('***************************')
        print('TEST_D4     EXPECT:', expected_max_flow)
        with open(filename, 'r') as file:
            count_vertices, count_edges = map(int, file.readline().split())
        source = 1
        sink = count_vertices
        start_time = time.time()  # Записываем время начала выполнения теста
        
        g = Graph.create_from_file(filename)
        max_flow = g.edmonds_karp(source, sink)
        end_time = time.time()  # Записываем время окончания выполнения теста
        execution_time = end_time - start_time  # Вычисляем время выполнения теста
        
        print(f"Время выполнения теста 'test_d4': {execution_time} секунд")
        self.assertEqual(max_flow, expected_max_flow)

    def test_d5(self):
        filename = "test_d5.txt"
        expected_max_flow = 3278  # Ожидаемый максимальный поток для этого графа
        print('***************************')
        print('TEST_D5     EXPECT:', expected_max_flow)
        with open(filename, 'r') as file:
            count_vertices, count_edges = map(int, file.readline().split())
        source = 1
        sink = count_vertices
        start_time = time.time()  # Записываем время начала выполнения теста
        
        g = Graph.create_from_file(filename)
        max_flow = g.edmonds_karp(source, sink)
        end_time = time.time()  # Записываем время окончания выполнения теста
        execution_time = end_time - start_time  # Вычисляем время выполнения теста
        
        print(f"Время выполнения теста 'test_d5': {execution_time} секунд")
        self.assertEqual(max_flow, expected_max_flow)

        

if __name__ == '__main__':
    unittest.main()
