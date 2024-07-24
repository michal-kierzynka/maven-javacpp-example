package eu.atspace.hash;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;


//@Platform(include="<vector>")
@Platform(include={"foo.h", "edit_distance.h"},
          link={"foo"})
@Namespace("FooLib")
public class FooLib {
	public static class Foo extends Pointer {
		static { Loader.load(); }

		public Foo() { allocate(); }

		private native void allocate();

		public native void setMethod(int a);
		public native int getMethod();

		public native @Cast("FooLib::Foo::Bool") int getBool();

		public native void getValues(@ByRef Values values);
		
		public native void setVector(@ByRef IntVector vec);
	}

    public static native int levenshtein(@ByVal @StdString String seq1, @ByVal @StdString String seq2);

	public static class Values extends Pointer {
		static { Loader.load(); } 

		public Values() { allocate(); }
		public Values(int size) { allocateArray(size); }

		private native void allocate();
		private native void allocateArray(int size);

		public native float x();
		public native Values x(float x);

		public native float y();
        public native Values y(float x);
	}
	
	@Name("::std::vector<int>")
	public static class IntVector extends Pointer {
		static { Loader.load(); }
		
		public IntVector()			{ allocate(); }
		public IntVector(long n)	{ allocate(n); }
		public IntVector(Pointer p)	{ super(p); }
		
		private native void allocate();
		private native void allocate(@Cast("size_t") long n);
				
		public native @Cast("size_t") long size();
		public native void resize(@Cast("size_t") long n);
				
		@Index @ByRef public native int get(@Cast("size_t") long i);
        public native IntVector put(@Cast("size_t") long i, int value);
	}

	
	public static void main(String[] args) {
		Foo foo = new Foo();
		System.out.println("initial value: " + foo.getMethod() + " is zero: " + foo.getBool());
		foo.setMethod(5);
		System.out.println("new value: " + foo.getMethod() +  " is zero: " + foo.getBool());
		
		Values v = new Values();
		foo.getValues(v);
		System.out.println("x: " + v.x() + " y: " + v.y());
		
		IntVector vect = new IntVector(3);
		System.out.println("vector size: " + vect.size());
		vect.put(0, 1);
		vect.put(1, 2);
		vect.put(2, 3);
		foo.setVector(vect);
		System.out.println("vector result: " + foo.getMethod());

		System.out.println("Levenstein: " + levenshtein("ABC123", "ABC1234"));

		// close resources
		try {
			foo.close();
			v.close();
			vect.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

