all: libfoo.so
	mkdir -p ../../../target/classes/eu/atspace/hash/linux-x86_64
	cp libfoo.so ../../../target/classes/eu/atspace/hash/linux-x86_64

clean:
	rm -f *.o *.so

libfoo.so: foo.o edit_distance.o
	g++ -shared -o libfoo.so foo.o edit_distance.o

foo.o: foo.cc foo.h
	g++ -c -fPIC foo.cc

edit_distance.o: edit_distance.cpp edit_distance.h
	g++ -c -fPIC -march=native -Wall -O3 edit_distance.cpp
