let
  var n: Integer;

  func inc(x: Integer): Integer ~
    x + 1
in
  begin
    getint(var n);
    putint(inc(n))
  end