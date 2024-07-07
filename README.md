<p style="text-align: center;">
  <b>BinaryHeap</b>
</p>

<p style="text-align: center;">
  <b>BinomialHeap</b>
</p>

<div style="display: flex; justify-content: center; align-items: center; height: 100vh;">
  <b> BlackBox </b>
</div>

<b> HashTable </b> 
<b> Huffman </b> 
Кодирование Хаффмана (D. A. Huffman) относится к префиксному кодированию, позволяющему минимизировать длину текста за счёт того, что различные символы кодируются различным числом битов.Напомним процесс построения кода.
Вначале строится дерево кода Хаффмана. Пусть исходный алфавит состоит из $n$ символов, $i$-й из которых встречается $p_i$ раз во входном тексте. Изначально все символы считаются активными вершинами будущего дерева, $i$-я вершина помечена значением $p_i$. На каждом шаге мы берём две активных вершины с наименьшими метками, создаём новую вершину, помечая её суммой меток этих вершин, и делаем её их родителем. Новая вершина становится активной, а двое её сыновей из списка активных вершин удаляются. Процесс многократно повторяется, пока не останется только одна активная вершина, которая полагается корнем дерева.Заметим, что символы алфавита представлены листьями этого дерева. Для каждого листа (символа) длина его кода Хаффмана равна длине пути от корня дерева до него.
Сам код строится следующим образом: для каждой внутренней вершины дерева рассмотрим две дуги, идущие от неё к сыновьям. Одной из дуг присвоим метку $0$, другой — $1$. Код каждого символа — последовательность из нулей и единиц на пути от корня к листу.Задача состоит в том, чтобы вычислить длину текста после его кодирования методом Хаффмана. Сам текст не дан, известно лишь, сколько раз каждый символ встречается в тексте. Этого достаточно для решения задачи, поскольку длина кода зависит только от частоты появления символов. Разработайте алгоритм, работающий за n, где n - количество частот появления символов.

<b> </b>  
<b> </b> 
<b> </b> 
<b> </b> 
