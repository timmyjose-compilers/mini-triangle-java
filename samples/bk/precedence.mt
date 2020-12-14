! Source: https://github.com/alyssa/MiniTriangleCompiler/blob/master/testFiles/precedence.mt

let 
  var sum: Integer;
  var x: Integer;
  var y: Integer;
  var z: Integer;
in
  begin
    sum := 0;
    x := 1;
    y := 2;
    z := 3;
    sum := x + y * z; ! sum = 7
    putint(sum);
  end
