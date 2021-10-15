package it.unibs.pajc;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.BinaryOperator;

public class CalcModel {
	
	
	
// lidea di reba consentiva di modificare gli Operatori con un array list per andare a creare un semplice hash map formata da array list 
	 public HashMap<Character,Operatori> operatori = 
			new HashMap<>();
	
	public CalcModel() {
		// INSERIRE QUESTA PARTE
		operatori.put('+', new BinaryOp() {
			@Override
			public int apply(int a, int b) {
				return a+ b;
			}
			 
		});
	
		operatori.put('-', new BinaryOp() {
			@Override
			public int apply(int a, int b) {
				return a- b;
			} 
		});
		
		operatori.put('*', new BinaryOp() {

			@Override
			public int apply(int a, int b) {
				return a* b;
			} 
		});
		
		operatori.put('/', new BinaryOp() {
			@Override
			public int apply(int a, int b) {
				return a/ b;
			} 
		});
		operatori.put('>', new BinaryOp() {
			@Override
			public int apply(int a, int b) {
				return a>b ? a : b;
			} 
		});
		operatori.put('R', new UnaryOp() {
			@Override
			public int apply(int a) {
				return (int)Math.sqrt(a);
			}

			
		});
		
	}
	
	
	

	public Set<Character> listOperator(){
		return operatori.keySet();
	}
	
	
	private class Opx {
		
		final boolean isOperator;
		final Character symbol;
		final Operatori operator;
		final Integer value;
		
		
		Opx(Integer value){
			isOperator = false;
			this.value = value;
			this.symbol = null;
			this.operator = null;
		}
		
		Opx(Character symbol, Operatori operator){
			isOperator =  true;
			this.value = null;
			this.symbol = symbol;
			this.operator = operator;
		}
		
		public String toString() {
			return isOperator ? ""+symbol : ""+value;
		}
		
		
		
	}
	
	Stack<Opx> calcStack = new Stack<>();
	
	public Integer pushOperand(Integer value) {
		calcStack.push(new Opx(value));
		
		return null;
	}
	
	public Integer pushOperator(Character symbol) {
		calcStack.push(new Opx(symbol, operatori.get(symbol)));
		
		return null;
	}

	public int eval() {
		
		EvaluateResult r = eval((Stack<Opx>)calcStack.clone());
		return r.result;
	}
	
	
	private class EvaluateResult{
		Integer result;
		Stack<Opx> remaingOpx;
		
		EvaluateResult(Integer result, Stack<Opx> remaingOpx){
			this.result = result;
			this.remaingOpx = remaingOpx;
		}
		
	}
	
	private EvaluateResult eval(Stack<Opx> opStack) {
		if(opStack.isEmpty())
			return new EvaluateResult(null,opStack);
		
		Stack<Opx> wStack = (Stack<Opx>)opStack.clone();
		
		Opx opx = wStack.pop();
		
		if(!opx.isOperator) { //opx è operando
			return new EvaluateResult(opx.value, wStack);
		}
		//  CODICE CON 2 OPERANDI 
		else if( opx.operator instanceof BinaryOp){
			EvaluateResult operando1 = eval(wStack);
			if(operando1.result == null)
				return new EvaluateResult(null,wStack);
			
			EvaluateResult operando2 = eval(operando1.remaingOpx);
			if(operando2.result == null)
				return new EvaluateResult(null,wStack);
			
			Integer result = ((BinaryOp) opx.operator).apply(operando1.result, operando2.result);
			
			
			return new EvaluateResult(result, operando2.remaingOpx);
			// CODICE CON UN SOLO OPERANDO
			// INSERIRE NEL TUO CODICE
		}else {
			EvaluateResult operando1 = eval(wStack);
			if(operando1 == null ) {
				return new EvaluateResult(null, wStack);
			}
			
			Integer result = ((UnaryOp)opx.operator).apply(operando1.result);
			
			return new EvaluateResult(result, operando1.remaingOpx);
		}
	}
	
	

	
	public String dump() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		
		for(Opx opx: calcStack) {
			sb.append(String.format("%s, ", opx));
		}
		
		sb.append("]");
		
		return sb.toString();
	}
	//
	
	
	// REVERSE
	
	
	public  void reverse () {
		if( calcStack.size() > 0 ) {
			Opx x = calcStack.peek();
			calcStack.pop();
			reverse();
			
			bottom_insert(x);
			
		}
	}
	
	public void bottom_insert(Opx x){
		if(calcStack.isEmpty()){
			calcStack.push(x);
		}else{
			Opx a = calcStack.peek();
			calcStack.pop();
			bottom_insert(x);
			calcStack.push(a);

		}

	}
	
	
	// REMOVE ELEMENT STACK
	
	public void removeElement () {
		do {
			calcStack.pop();
		}while(!calcStack.isEmpty());
	}
	
	
	
	
	

	
}
