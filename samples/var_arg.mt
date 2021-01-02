let 
  proc S(var n: Integer, i: Integer) ~
    begin
      n := n + i;
      putint(n)
    end;

  var b: record
          y: Integer,
          m: Integer,
          d: Integer
         end
in
  begin
    b := {y ~ 1978, m ~ 5, d ~ 5};
    S(var b.m, 6)
  end