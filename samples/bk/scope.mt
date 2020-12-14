! Source: https://github.com/alyssa/MiniTriangleCompiler/blob/master/testFiles/scope.mt

let 
  var x: Integer;
  var y: Integer
in
  begin
    x := 1;
    y := 2;
    let
      var x: Integer;
    in
      begin
        let
          var y: Integer;
        in
          getint(y); ! let's call this yin
        x := y; ! this is the inner x
      end
    putint(y); ! this should be 2
    putint(x); ! this should be 1
  end
