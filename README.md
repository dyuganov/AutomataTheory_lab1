Грамматика имеет вид G = ( N , ∑ , P , S ), где + \
N - алфавит нетерминальных символов (может состоять из всего, кроме {',\",-,>,} ) \
∑ - алфавит терминальных символов (может состоять из всего, кроме N⋃{',\",-,>,} ) \
P - конечное множество правил 

Правила могут иметь вид a->b , где a∈(N⋃∑)*N(N⋃∑)*, ba∈(N⋃∑)* \
S - выделенный символ из N, называемый начальным символом \
КС-грамматика:каждое правило из Р имеет вид А->a, где A∈N, a∈(N⋃∑)* \
e - зарезервированный символ для пустого слова, он может появиться только в правилах!

ПримерКС1: \
N = S,T \
∑ = a,b,c \
P = S->TT,T->cTT,T->bT,T->a \
S = S \
Цепочки: aa , caa , cbaa , bcaa , ...

ПримерКС2:
N = S \
∑ = 0,1 \
P = S->0S1,S->1S0,S->S01,S->S10,S->e \
S = S \
Цепочки: любая из 0 и 1, количество вхождений 0 и 1 одинаково