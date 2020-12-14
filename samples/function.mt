! Source: https://github.com/alyssa/MiniTriangleCompiler/blob/master/testFiles/function.mt

! Demonstration of a simple function in Mini-Triangle

let 
  var x: Integer;
  var y: Integer;
  var z: Integer;
  func foo(x: Integer, y: Integer): Integer
    begin
      x := x + 1; ! increment x
      return x + y;
    end
in
  begin
    x := 3;
    y := 4;
    z := foo(x, y); ! z is 8
    putint(z);
  end
