mkdir books-input


create download_books.sh

```shell
for i in {1340..1400} 
do 
    wget "http://www.gutenberg.org/files/$i/$i.txt" 
done
```

./download_books.sh

write mapper.py

```python
#!/usr/bin/env python3 

import sys 
import string 

for line in sys.stdin: 
    line = line.strip() 
    words = line.split() 
    for w in words: 
        table = w.maketrans('', '', string.punctuation)
        w = w.translate(table).lower() 
        print(w, '\t', 1)
```

write reducer.py

```python
#!/usr/bin/env python3
from collections import defaultdict
import sys
word_count = defaultdict(int)

for line in sys.stdin:
    try:
        line = line.strip()
        word, count = line.split()
        count = int(count)
    except:
        continue

word_count[word] += count

for word, count in word_count.items():
    print(word, count)
```

test

```shell
printf 'My name is Karim\nWhat is your name' | ./mapper.py | ./reducer.py
```

Using hadoop

```shell
hdfs dfs -mkdir books-input hdfs dfs -put books-input/*.txt books-input
```

```find /usr/lib/ -name *hadoop*streaming*.jar``` to find jar

RUN

```shell
hadoop jar /usr/lib/hadoop/hadoop-streaming.jar -files mapper.py,reducer.py -mapper mapper.py -reducer reducer.py -in put books-input -output books-output
```
