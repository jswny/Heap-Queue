package structures;

import java.util.Comparator;
import java.util.Iterator;

public class StudentArrayHeap<P, V> extends AbstractArrayHeap<P, V> {
	
	protected StudentArrayHeap(Comparator<P> comparator) {
		super(comparator);
	}

	@Override
	protected int getLeftChildOf(int index) {
		if (index < 0)
			throw new IndexOutOfBoundsException();
		int i = (index * 2) + 1;
		return i;
	}

	@Override
	protected int getRightChildOf(int index) {
		if (index < 0)
			throw new IndexOutOfBoundsException();
		int i = (index * 2) + 2;
		return i;
	}

	@Override
	protected int getParentOf(int index) {
		if (index < 1)
			throw new IndexOutOfBoundsException();
		int i = (index - 1) / 2;
		return i;
	}

	@Override
	protected void bubbleUp(int index) {
		
		Entry<P, V> tempVal;
		int tempIndex;
		
//		V parent = aList.get(this.getParentOf(index));
//		V node = aList.get(index);
		
		if (index == 0)
			return;
		
		while (comparator.compare(  heap.get(this.getParentOf(index)).getPriority(),  heap.get(index).getPriority()) < 0) {
			
			tempIndex = this.getParentOf(index);
			
			tempVal = heap.get(tempIndex);
			
			heap.set(tempIndex, heap.get(index));
			
			heap.set(index, tempVal);
			
			index = tempIndex;
			
			if (index == 0) return;
		}
		
	}

	@Override
	protected void bubbleDown(int index) {
		Entry<P, V> tempVal;
		int tempIndex = 0;
		
		if (heap.size() <= 1)
			return;
		
		while (true) {
			
			//int comp1 = comparator.compare(  heap.get(this.getLeftChildOf(index)).getPriority(),  heap.get(index).getPriority());
			
			if (getLeftChildOf(index) >= heap.size()) 
				return;
			
			//int comp2 = comparator.compare(  heap.get(this.getRightChildOf(index)).getPriority(),  heap.get(index).getPriority());
			
			if (getRightChildOf(index) >= heap.size()) {
				if (comparator.compare(  heap.get(this.getLeftChildOf(index)).getPriority(),  heap.get(index).getPriority()) > 0) {
					tempIndex = getLeftChildOf(index);
				} else {
					return;
				}
			} else if (comparator.compare(  heap.get(this.getLeftChildOf(index)).getPriority(),  heap.get(index).getPriority()) > 0 || comparator.compare(heap.get(this.getRightChildOf(index)).getPriority(), heap.get(index).getPriority()) > 0) {
				if (comparator.compare( heap.get(this.getLeftChildOf(index)).getPriority(),  heap.get(this.getRightChildOf(index)).getPriority()) >= 0) {
					tempIndex = this.getLeftChildOf(index);
				} else {
					tempIndex = this.getRightChildOf(index);
				}
			} else {
				return;
			}
			
			tempVal = heap.get(index);
			
			heap.set(index, heap.get(tempIndex));
			
			heap.set(tempIndex, tempVal);
			
			index = tempIndex;
		}
		
	}
	
	public Iterator<Entry<P, V>> iterator() {
		return heap.iterator();
	}
}
