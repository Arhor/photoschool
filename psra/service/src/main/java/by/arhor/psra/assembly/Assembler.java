package by.arhor.psra.assembly;

public interface Assembler<From, To> {

	To assemble(From item);
	
	From disassemle(To item);
	
}
