! Source: https://github.com/alyssa/MiniTriangleCompiler/tree/master/testFiles/factorial.mt

! Calculate the factorial of the integer read in from stdin.

let 
  var x: Integer;
  var fact: Integer
in
  begin
    x := 5;
    fact := 1;

    while x > 0 do
      begin
        fact := fact * x;
        x :=  x - 1
      end;
    putint(fact)
  end