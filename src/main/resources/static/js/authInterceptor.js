app.factory('AuthInterceptor', [
    function () {
        return {
            'request': function (config) {
                var encodedString = btoa("user:123456");
                config.headers = config.headers || {};
                config.headers.Authorization = 'Basic ' + encodedString;
                return config;
            }
        };
    }
]);